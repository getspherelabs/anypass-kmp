package io.spherelabs.crypto.tinypass.database.serializer.internal

import io.spherelabs.crypto.cipher.AES
import io.spherelabs.crypto.cipher.ChaCha7539Engine
import io.spherelabs.crypto.cipher.CipherPadding
import io.spherelabs.crypto.tinypass.database.EncryptionSaltGenerator
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.buffer.WriterStrategy
import io.spherelabs.crypto.tinypass.database.core2.*
import io.spherelabs.crypto.tinypass.database.header.CipherId
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import io.spherelabs.crypto.tinypass.database.xml.XmlReader
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
            println("Outer header is ${outerHeaderBuffer.snapshot()}")
            sink.write(outerHeaderBuffer.snapshot().toByteArray())

            println("Raw is ${raw.toByteString()}")
            val encryptedContent = serializeAsContent(
                cipherId = outerHeader.option.cipherId,
                key = masterKey(masterSeed = seed, outerHeader = outerHeader, configuration),
                iv = outerHeader.encryptionIV.toByteArray(),
                data = raw,
            )

            println("Encrypted in writing is ${encryptedContent.toByteString()}")
            ContentBlocks.writeContentBlocksVer4x(
                sink = sink,
                contentData = encryptedContent,
                masterSeed = seed,
                transformedKey = transformKey(header = outerHeader, configuration),
            )
            println("Writer Buffer is $sink")
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
            println("Deserialized key is ${key.toByteString()}")
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

private fun serializeAsContent(
    cipherId: CipherId,
    key: ByteArray,
    iv: ByteArray,
    data: ByteArray,
): ByteArray {
    return when (cipherId) {
        CipherId.Aes -> {
            println("Serialized key is ${key.toByteString()}")
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


fun commonKdbxDecode(fileSystem: BufferedSource, database: KdbxDatabase): KdbxDatabase =
    database.apply {
        val headerBuffer = Buffer()

        fileSystem.use { source ->
            println("2. Decode file system ${fileSystem.buffer.snapshot()}")
            source.buffer.write(headerBuffer, headerBuffer.size)
            val header = KdbxBuffer.readOuterHeader(source)

            val rawHeaderData = headerBuffer.snapshot()

            val transformedKey = transformKey(header, configuration)
            val expectedSha256 = source.readByteString(32)
            val expectedHmac = source.readByteString(32)
            val seed = header.seed.toByteArray()

            val encryptedContent = ContentBlocks.readContentBlocksVer4x(
                source = source,
                masterSeed = seed,
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
