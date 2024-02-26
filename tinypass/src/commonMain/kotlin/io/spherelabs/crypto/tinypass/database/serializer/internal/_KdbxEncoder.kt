package io.spherelabs.crypto.tinypass.database.serializer.internal

import io.spherelabs.crypto.cipher.AES
import io.spherelabs.crypto.cipher.ChaCha7539Engine
import io.spherelabs.crypto.cipher.CipherPadding
import io.spherelabs.crypto.tinypass.database.EncryptionSaltGenerator
import io.spherelabs.crypto.tinypass.database.buffer.KdbxBuffer
import io.spherelabs.crypto.tinypass.database.compressor.toGzipSink
import io.spherelabs.crypto.tinypass.database.compressor.toGzipSource
import io.spherelabs.crypto.tinypass.database.core.*
import io.spherelabs.crypto.tinypass.database.getPlatformFileSystem
import io.spherelabs.crypto.tinypass.database.header.CipherId
import io.spherelabs.crypto.tinypass.database.header.CompressionFlags
import io.spherelabs.crypto.tinypass.database.serializer.KdbxSerializer
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import io.spherelabs.crypto.tinypass.database.xml.XmlReader
import io.spherelabs.crypto.tinypass.database.xml.XmlWriter
import okio.Buffer
import okio.Path.Companion.toPath
import okio.buffer
import okio.use

fun commonKdbxEncode(database: KdbxDatabase) = database.apply {
    val outerHeaderBuffer = Buffer().also { sink ->
        KdbxBuffer.writeOuterHeader(outerHeader = outerHeader, sink = sink)
    }

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

    val rawContent = XmlWriter.write(
        query, option,
    ).text().encodeToByteArray()

    val contentBuffer = Buffer()
    KdbxBuffer.writeInnerHeader(sink = contentBuffer, innerHeader)
    contentBuffer.write(rawContent)

    var raw = contentBuffer.readByteArray()

    if (outerHeader.option.compressionFlags == CompressionFlags.GZip) {
        raw = raw.toGzipSink().buffer().buffer.readByteArray()
    }

    val seed = outerHeader.seed.toByteArray()

    val encryptedContent = serializeAsContent(
        cipherId = outerHeader.option.cipherId,
        key = masterKey(masterSeed = seed, outerHeader = outerHeader, configuration),
        iv = outerHeader.encryptionIV.toByteArray(),
        data = raw,
    )

    getPlatformFileSystem().write(
        configuration.path.toPath(),
        mustCreate = true
    ) {
        buffer().use { sink ->
            sink.write(outerHeaderBuffer.snapshot().toByteArray())
            ContentBlocks.writeContentBlocksVer4x(
                sink = sink,
                contentData = encryptedContent,
                masterSeed = seed,
                transformedKey = transformKey(header = outerHeader, configuration),
            )
        }
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


fun commonKdbxDecode(database: KdbxDatabase): KdbxDatabase  = database.apply {
    val headerBuffer = Buffer()

    getPlatformFileSystem().read(configuration.path.toPath()) {
        val header = KdbxBuffer.readOuterHeader()

        val rawHeaderData = headerBuffer.snapshot()

        val expectedSha256 = readByteString(32)
        val expectedHmacSha256 = readByteString(32)
        val transformedKey = transformKey(header, configuration)
        val innerHeaderBuffer = Buffer()
        val seed = header.seed.toByteArray()
        val encryptedContent = ContentBlocks.readContentBlocksVer4x(
            source = this,
            masterSeed = seed,
            transformedKey = transformedKey,
        )
        var decryptedContent =
            deserializeAsContent(
                header.option.cipherId, key = masterKey(seed, header, configuration),
                iv = header.encryptionIV.toByteArray(), data = encryptedContent,
            )

        if (outerHeader.option.compressionFlags == CompressionFlags.GZip) {
            decryptedContent = decryptedContent.toGzipSink().buffer().buffer.readByteArray()
        }

        val innerHeader = KdbxBuffer.readInnerHeader()
        val saltGenerator = EncryptionSaltGenerator.create(
            id = innerHeader.streamCipher,
            key = innerHeader.streamKey
        )

        val content = XmlReader
    }
}
