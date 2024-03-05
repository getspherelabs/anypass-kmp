package io.spherelabs.crypto.tinypass.database.serializer.internal

import io.spherelabs.crypto.cipher.AES
import io.spherelabs.crypto.cipher.ChaCha7539Engine
import io.spherelabs.crypto.cipher.CipherPadding
import io.spherelabs.crypto.tinypass.database.EncryptionSaltGenerator
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.buffer.WriterStrategy
import io.spherelabs.crypto.tinypass.database.compressor.gzip
import io.spherelabs.crypto.tinypass.database.core.*
import io.spherelabs.crypto.tinypass.database.core.internal.HmacBlock
import io.spherelabs.crypto.tinypass.database.header.CipherId
import io.spherelabs.crypto.tinypass.database.header.CompressionFlags
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import io.spherelabs.crypto.tinypass.database.xml.XmlWriter
import okio.*
import okio.ByteString.Companion.toByteString

fun commonKdbxEncode(fileSystem: BufferedSink, database: KdbxDatabase) =
    database.apply {
        val outerHeaderBuffer = Buffer().also { sink ->
            KdbxBuffer.write(sink = sink, strategy = WriterStrategy.Outer(outerHeader))
        }

        val headerHash = outerHeaderBuffer.sha256()

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

        val hmacKey = hmacKey(
            masterSeed = outerHeader.seed.toByteArray(),
            outerHeader = outerHeader,
            config = configuration,
        )

        val hmacSha256 = outerHeaderBuffer.hmacSha256(hmacKey.toByteString())

        outerHeaderBuffer.write(headerHash)
        outerHeaderBuffer.write(hmacSha256)

        val xmlText = XmlWriter.write(
            query, option,
        )

        val rawContent = xmlText.encodeToByteArray()

        val innerHeaderBuffer = Buffer()
        KdbxBuffer.write(sink = innerHeaderBuffer, WriterStrategy.Inner(innerHeader))
        innerHeaderBuffer.write(rawContent)

        val raw = innerHeaderBuffer.readByteArray()

        val seed = outerHeader.seed.toByteArray()

        fileSystem.use { sink ->
            sink.write(outerHeaderBuffer.snapshot().toByteArray())

            val encryptedContent = serializeAsContent(
                cipherId = outerHeader.option.cipherId,
                key = masterKey(masterSeed = seed, outerHeader = outerHeader, configuration),
                iv = outerHeader.encryptionIV.toByteArray(),
                data = raw,
            )

            HmacBlock.write(
                sink = sink,
                bytes = encryptedContent,
                seed = seed,
                transformedKey = transformKey(header = outerHeader, configuration),
            )
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



