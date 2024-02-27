package io.spherelabs.crypto.tinypass.database.buffer.internal

import io.spherelabs.crypto.tinypass.database.common.toIntLe
import io.spherelabs.crypto.tinypass.database.common.toUuid
import io.spherelabs.crypto.tinypass.database.header.*
import io.spherelabs.crypto.tinypass.database.model.component.BinaryData
import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature
import okio.BufferedSource
import okio.ByteString

internal fun BufferedSource.commonReadOuterHeader(): KdbxOuterHeader {
    var cipherId: CipherId? = null
    var compressionFlags: CompressionFlags? = null

    var seed: ByteString? = null
    var encryptionIV: ByteString? = null
    var params: KdfParameters? = null
    var customData: Map<String, Kdbx4Field> = mapOf()

    val kdbxSignature = KdbxSignature.deserialize(this)
    val kdbxVersion = KdbxVersion.deserialize(this)

    while (true) {
        val (id, rawData) = buffer.commonReadId(kdbxVersion)
        when (FieldID.values().getOrNull(id)) {
            FieldID.End -> break
            FieldID.CipherID -> {
                cipherId = checkNotNull(
                    rawData.toUuid()?.let {
                        CipherId.values().firstOrNull { it.uuid == it.uuid }
                    },
                )
            }

            FieldID.CompressionFlags -> {
                compressionFlags = CompressionFlags.values()[rawData.toIntLe()]
            }

            FieldID.Seed -> {
                seed = rawData
            }

            FieldID.EncryptionIV -> encryptionIV = rawData
            FieldID.KdfParameters -> {
                params = KdfParameters.deserialize(rawData)
            }

            FieldID.PublicCustomData -> customData = VarDict.deserialize(rawData)
            else -> {}
        }
    }

    return KdbxOuterHeader(
        option = OuterHeaderOption(
            kdbxSignature = kdbxSignature,
            kdbxVersion = kdbxVersion,
            cipherId = checkNotNull(cipherId),
            compressionFlags = checkNotNull(compressionFlags),
        ),
        seed = checkNotNull(seed),
        encryptionIV = checkNotNull(encryptionIV),
        kdfParameters = checkNotNull(params),
        customData = customData,
    )
}

internal fun BufferedSource.commonReadInnerHeader(): KdbxInnerHeader {
    val binaries = linkedMapOf<ByteString, BinaryData>()
    var streamCipher: CrsAlgorithm? = null
    var streamKey: ByteString? = null

    println(this.buffer.snapshot())
    while (true) {
        val id = this.readByte()
        val length = this.readIntLe().toLong()

        when (id.toInt()) {
            KdbxInnerHeader.END_OF_HEADER -> {
                println("End of header")
                this.readByteArray(length)
                break
            }
            KdbxInnerHeader.STREAM_CIPHER -> {
                println("Stream of cipher")
                streamCipher = CrsAlgorithm.values()[this.readIntLe()]
            }
            KdbxInnerHeader.STREAM_KEY -> {
                println("Stream key")
                streamKey = this.readByteString(length)
            }
            KdbxInnerHeader.BINARY -> {
                println("Binary")
                val memoryProtection = this.readByte() != 0x0.toByte()
                val content = this.readByteArray(length - 1)
                val binary = BinaryData.Uncompressed(memoryProtection, content)
                binaries[binary.hash] = binary

            }
            else -> {}
        }
    }

    return KdbxInnerHeader(
        streamCipher = requireNotNull(streamCipher) { "Stream cipher is not existed." },
        streamKey = requireNotNull(streamKey) { "Stream key is not existed." },
        binaries = binaries,
    )
}

internal fun BufferedSource.commonReadSignature(): KdbxSignature {
    return KdbxSignature(
        first = readByteString(4),
        second = readByteString(4),
    )
}

internal fun BufferedSource.commonReadId(
    kdbxVersion: KdbxVersion,
): Pair<Int, ByteString> {
    val id = readByte()
    val length = readIntLe().toLong()
    val data = if (length > 0) {
        readByteString(length)
    } else {
        ByteString.EMPTY
    }
    return id.toInt() to data
}
