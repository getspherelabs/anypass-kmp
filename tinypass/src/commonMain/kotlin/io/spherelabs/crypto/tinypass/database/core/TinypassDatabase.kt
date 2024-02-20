package io.spherelabs.crypto.tinypass.database.core

import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.cipher.AES
import io.spherelabs.crypto.cipher.ChaCha7539Engine
import io.spherelabs.crypto.cipher.CipherPadding
import io.spherelabs.crypto.tinypass.database.EncryptionSaltGenerator
import io.spherelabs.crypto.tinypass.database.compressor.toGzipSource
import io.spherelabs.crypto.tinypass.database.header.CipherId
import io.spherelabs.crypto.tinypass.database.header.CompressionFlags
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.DatabaseContent
import io.spherelabs.crypto.tinypass.database.model.component.Group
import io.spherelabs.crypto.tinypass.database.model.component.Meta
import okio.*

/**
 * Option
 *
 */
data class TinypassDatabase(
    val credentials: Credentials,
    val header: KdbxOuterHeader,
    val content: DatabaseContent,
    val kdbxInnerHeader: KdbxInnerHeader,
) {
    companion object {
        fun build(
            credentials: Credentials,
            rootName: String,
            meta: Meta,
        ): TinypassDatabase {
            return TinypassDatabase(
                credentials = credentials,
                header = KdbxOuterHeader.create(),
                content = DatabaseContent(
                    meta = meta,
                    group = Group(
                        id = uuid4(),
                        title = rootName,
                        isSearchable = true,
                        isAutoTyped = true,
                    ),
                    deletedObjects = listOf(),
                ),
                kdbxInnerHeader = KdbxInnerHeader.create(),
            )
        }

        fun deserialize(
            path: String,
            source: BufferedSource,
            keyHelper: KeyHelper,
        ): TinypassDatabase {
            val headerBuffer = Buffer()
            source.use { bs ->
                with(bs) {
                    val header = KdbxOuterHeader.deserialize(bs)
                    val raw = headerBuffer.snapshot()
                    val transformKey = transformKey(header, keyHelper)

                    val sha256 = readByteString(32)
                    val sha512 = readByteString(32)

                    val innerHeaderBuffer = Buffer()

                    val contentBuffer =
                        Buffer().write(deserializeRawContent(header, source, transformKey))
                    val contentSource = (buffer as Source).buffer()

                    val kdbxInnerHeader = KdbxInnerHeader.deserialize(contentSource)
                    val saltGenerator = EncryptionSaltGenerator.create(
                        id = kdbxInnerHeader.streamCipher,
                        key = kdbxInnerHeader.streamKey,
                    )
                    //val content = content
                    TODO()
                }
            }
        }

        private fun deserializeRawContent(
            header: KdbxOuterHeader,
            source: BufferedSource,
            transformKey: ByteArray,
        ): ByteArray {
            val seed = header.seed.toByteArray()
            val encryptedContent = ContentBlocks.readContentBlocksVer4x(source, seed, transformKey)
            var decryptedContent =
                deserializeAsContent(
                    header.option.cipherId, key = masterKey(seed, transformKey),
                    iv = header.encryptionIV.toByteArray(), data = encryptedContent,
                )
            if (header.option.compressionFlags == CompressionFlags.GZip) {
                decryptedContent = try {
                    decryptedContent.toGzipSource().buffer().readByteArray()
                } catch (e: Exception) {
                    throw e
                }
            }
            return decryptedContent
        }

        private fun deserializeAsContent(
            cipherId: CipherId,
            key: ByteArray,
            iv: ByteArray,
            data: ByteArray,
        ): ByteArray {
            return when (cipherId) {
                CipherId.Aes -> {
                    ChaCha7539Engine().apply { init(key, iv) }.processBytes(data)
                }
                CipherId.ChaCha20 -> {
                    AES.decryptAesCbc(
                        key = key,
                        data = data,
                        iv = iv,
                        padding = CipherPadding.NoPadding,
                    )
                }
            }
        }

        private fun serializeAsContent(
            cipherId: CipherId,
            key: ByteArray,
            iv: ByteArray,
            data: ByteArray,
        ): ByteArray {
            return when (cipherId) {
                CipherId.Aes -> {
                    AES.encryptAesCbc(
                        key = key,
                        iv = iv,
                        data = data,
                        padding = CipherPadding.NoPadding,
                    )
                }
                CipherId.ChaCha20 -> {
                    ChaCha7539Engine().apply { init(key, iv) }.processBytes(data)
                }
            }
        }
    }
}

interface Deserializer {
    fun read()
}

interface Serializer {
    fun write()
}
