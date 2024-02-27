package io.spherelabs.crypto.tinypass.database.serializer.internal

import io.spherelabs.crypto.cipher.AES
import io.spherelabs.crypto.cipher.ChaCha7539Engine
import io.spherelabs.crypto.cipher.CipherPadding
import io.spherelabs.crypto.tinypass.database.EncryptionSaltGenerator
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer.buffer
import io.spherelabs.crypto.tinypass.database.buffer.internal.commonReadInnerHeader
import io.spherelabs.crypto.tinypass.database.compressor.toGzipSink
import io.spherelabs.crypto.tinypass.database.compressor.toGzipSource
import io.spherelabs.crypto.tinypass.database.core.*
import io.spherelabs.crypto.tinypass.database.getPlatformFileSystem
import io.spherelabs.crypto.tinypass.database.header.CipherId
import io.spherelabs.crypto.tinypass.database.header.CompressionFlags
import io.spherelabs.crypto.tinypass.database.header.CrsAlgorithm
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.model.component.BinaryData
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import io.spherelabs.crypto.tinypass.database.xml.XmlReader
import io.spherelabs.crypto.tinypass.database.xml.XmlWriter
import okio.*
import okio.ByteString.Companion.toByteString
import okio.Path.Companion.toPath

fun commonKdbxEncode(fileSystem: Buffer, database: KdbxDatabase) =
    database.apply {
        val outerHeaderBuffer = Buffer().also { sink ->
            KdbxBuffer.writeOuterHeader(outerHeader = outerHeader, sink = sink)
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
            query, option
        )

        println("Xml text is $xmlText")
        val rawContent =xmlText.encodeToByteArray()

        val innerHeaderBuffer = Buffer()
        KdbxBuffer.writeInnerHeader(innerHeaderBuffer, innerHeader)
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
            source.buffer.write(headerBuffer, headerBuffer.size)
            val header = KdbxBuffer.readOuterHeader(source)

            val rawHeaderData = headerBuffer.snapshot()

            val transformedKey = transformKey(header, configuration)
            val expectedSha256 = source.readByteString(32)
            println("Expected sha256 is $expectedSha256")
            val expectedHmac = source.readByteString(32)
            println("Expected hmac is $expectedHmac")
            val seed = header.seed.toByteArray()

            val encryptedContent = ContentBlocks.readContentBlocksVer4x(
                source = source,
                masterSeed = seed,
                transformedKey = transformedKey,
            )


            println("Encrypted content is ${encryptedContent.toByteString()}")
            println("Cipher id is ${header.option.cipherId}")
            val decryptedContent =
                deserializeAsContent(
                    header.option.cipherId,
                    key = masterKey(masterSeed = seed, outerHeader = outerHeader, configuration),
                    iv = header.encryptionIV.toByteArray(),
                    data = encryptedContent,
                )


            println("Decrypted content is ${decryptedContent.toByteString()}")
            val innerHeaderBuffer = Buffer().apply {
                write(decryptedContent)
            }

            val innerSource = (innerHeaderBuffer as BufferedSource)

            val innerHeader = KdbxBuffer.readInnerHeader(innerSource)

            println("Inner header is $innerHeader")
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

            val content = XmlReader.read(innerSource, option)

            println("Content is $content")

            return KdbxDatabase(
                configuration = configuration,
                outerHeader = header,
                innerHeader = innerHeader,
                query = content,
            )
        }

    }
