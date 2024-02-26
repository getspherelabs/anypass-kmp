package io.spherelabs.crypto.tinypass.database.serializer

import com.benasher44.uuid.uuid4
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.cipher.AES
import io.spherelabs.crypto.cipher.ChaCha7539Engine
import io.spherelabs.crypto.cipher.CipherPadding
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.compressor.toGzipSink
import io.spherelabs.crypto.tinypass.database.core.ContentBlocks
import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import io.spherelabs.crypto.tinypass.database.core.masterKey
import io.spherelabs.crypto.tinypass.database.core.transformKey
import io.spherelabs.crypto.tinypass.database.getPlatformFileSystem
import io.spherelabs.crypto.tinypass.database.header.CipherId
import io.spherelabs.crypto.tinypass.database.header.CompressionFlags
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.header.KdfParameters
import io.spherelabs.crypto.tinypass.database.model.component.KdbxQuery
import io.spherelabs.crypto.tinypass.database.model.component.Group
import io.spherelabs.crypto.tinypass.database.model.component.Meta
import io.spherelabs.crypto.tinypass.database.serializer.internal.commonKdbxEncode
import okio.Buffer
import okio.BufferedSource
import okio.ByteString.Companion.toByteString
import okio.Path.Companion.toPath
import okio.buffer

object KdbxSerializer : KdbxEncoder, KdbxDecoder {

    override fun encode(config: KdbxConfiguration): KdbxDatabase {
        val random = buildSecureRandom()
        val outerHeader = KdbxOuterHeader.create().copy(
            seed = random.nextBytes(32).toByteString(),
            encryptionIV = random.nextBytes(16).toByteString(),
            kdfParameters = KdfParameters.Argon2(
                uuid = KdfParameters.KdfArgon2d,
                salt = random.nextBytes(32).toByteString(),
                parallelism = 2U,
                memory = 32UL * 1024UL * 1024UL,
                iterations = 8U,
                version = 0x13.toUInt(),
                key = null,
                associatedData = null,
            ),
            customData = linkedMapOf(),
        )
        val innerHeader = KdbxInnerHeader.of(
            random.nextBytes(64).toByteString(),
        )
        val meta = Meta(
            name = config.path,
        )

        return commonKdbxEncode(
            KdbxDatabase(
                configuration = config,
                outerHeader = outerHeader,
                innerHeader = innerHeader,
                query = KdbxQuery(
                    meta = meta,
                    group = Group(
                        id = uuid4(),
                        title = config.path,
                        isAutoTyped = true,
                        isSearchable = true,
                    ),
                    deletedObjects = emptyList(),
                ),
            ),
        )

    }

    override fun decode(source: BufferedSource, configuration: KdbxConfiguration): KdbxDatabase {
      TODO()
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
}
