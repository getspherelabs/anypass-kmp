package io.spherelabs.crypto.tinypass.database.serializer.internal

import io.spherelabs.crypto.cipher.AES
import io.spherelabs.crypto.cipher.ChaCha7539Engine
import io.spherelabs.crypto.cipher.CipherPadding
import io.spherelabs.crypto.tinypass.database.EncryptionSaltGenerator
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.compressor.ungzip
import io.spherelabs.crypto.tinypass.database.core.internal.HmacBlock
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import io.spherelabs.crypto.tinypass.database.core.masterKey
import io.spherelabs.crypto.tinypass.database.core.transformKey
import io.spherelabs.crypto.tinypass.database.header.CipherId
import io.spherelabs.crypto.tinypass.database.header.CompressionFlags
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import io.spherelabs.crypto.tinypass.database.xml.XmlReader
import okio.Buffer
import okio.BufferedSource
import okio.use

fun commonKdbxDecode(buffer: BufferedSource, database: KdbxDatabase): KdbxDatabase =
    database.apply {
        val headerBuffer = Buffer()

        buffer.use { source ->
            source.buffer.write(headerBuffer, headerBuffer.size)
            val header = KdbxBuffer.readOuterHeader(source)

            val rawHeaderData = headerBuffer.snapshot()

            val transformedKey = transformKey(header, configuration)

            val expectedSha256 = source.readByteString(32)
            val expectedHmac = source.readByteString(32)

            val seed = header.seed.toByteArray()

            val encryptedContent = HmacBlock.read(
                source = source,
                seed = seed,
                transformedKey = transformedKey,
            )



            val decryptedContent =
                deserializeAsContent(
                    header.option.cipherId,
                    key = masterKey(masterSeed = seed, outerHeader = outerHeader, configuration),
                    iv = header.encryptionIV.toByteArray(),
                    data = encryptedContent,
                )

            val innerHeaderBuffer = Buffer().apply {
                write(decryptedContent)
            }

            val innerHeader = KdbxBuffer.readInnerHeader(innerHeaderBuffer)

            val salt = EncryptionSaltGenerator.create(
                id = innerHeader.streamCipher,
                key = innerHeader.streamKey,
            )

            val option = XmlOption(
                kdbxVersion = outerHeader.option.kdbxVersion,
                salt = salt,
                binaries = innerHeader.binaries,
                isExportable = false,
            )

            val content = XmlReader.read(innerHeaderBuffer, option)

            return KdbxDatabase(
                configuration = configuration,
                outerHeader = header,
                innerHeader = innerHeader,
                query = content,
            )
        }

    }

private fun deserializeAsContent(
    cipherId: CipherId,
    key: ByteArray,
    iv: ByteArray,
    data: ByteArray,
): ByteArray {
    return when (cipherId) {
        CipherId.Aes -> {
            AES.decryptAesCbc(
                key = key,
                data = data,
                iv = iv,
                padding = CipherPadding.NoPadding,
            )

        }
        CipherId.ChaCha20 -> {
            ChaCha7539Engine().apply { init(key, iv) }.processBytes(data)
        }
    }
}
