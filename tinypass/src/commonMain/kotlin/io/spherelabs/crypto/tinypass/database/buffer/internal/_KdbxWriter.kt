package io.spherelabs.crypto.tinypass.database.buffer.internal

import io.spherelabs.crypto.tinypass.database.buffer.KdbxConstants
import io.spherelabs.crypto.tinypass.database.header.*
import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature
import okio.Buffer
import okio.BufferedSink
import okio.ByteString

private fun BufferedSink.commonWriteHeaderOption(headerOption: OuterHeaderOption) {
    commonWriteSignature(headerOption.kdbxSignature)
    commonWriteVersion(headerOption.kdbxVersion)
    writeByte(FieldID.CipherID.ordinal)
    writeIntLe(OuterHeaderOption.CIPHER_ID_LENGTH)
    val bytes = Buffer().apply {
        writeLong(headerOption.cipherId.uuid.mostSignificantBits)
        writeLong(headerOption.cipherId.uuid.leastSignificantBits)
    }.readByteString()

    write(bytes)

    writeByte(FieldID.CompressionFlags.ordinal)
    writeIntLe(Int.SIZE_BYTES)
    writeIntLe(headerOption.compressionFlags.ordinal)
}

internal fun BufferedSink.commonWriteInnerHeader(header: KdbxInnerHeader) {
    writeByte(KdbxConstants.STREAM_CIPHER)
    writeIntLe(Int.SIZE_BYTES)
    writeIntLe(header.streamCipher.ordinal)

    println("Stream cipher is ${header.streamCipher}")
    writeByte(KdbxConstants.STREAM_KEY)
    writeIntLe(header.streamKey.size)
    write(header.streamKey)

    println("Stream key is ${header.streamKey}")

    for ((_, binary) in header.binaries) {
        val data = binary.getContent()
        println("Data is $data")
        writeByte(KdbxConstants.BINARY)
        writeIntLe(data.size + 1)
        writeByte(if (binary.memoryProtection) 0x1 else 0x0)
        write(data)
    }

    writeByte(KdbxConstants.END_OF_HEADER)
    writeIntLe(0)
}


internal fun BufferedSink.commonWriteOuterHeader(header: KdbxOuterHeader) {
    commonWriteHeaderOption(header.option)
    writeField(FieldID.Seed, header.seed)
    writeField(FieldID.EncryptionIV, header.encryptionIV)

    val params = commonKdfParameters(header.kdfParameters)
    writeField(FieldID.KdfParameters, params)

    val customData = commonWriteVarDict(header.customData)
    writeField(FieldID.PublicCustomData, customData)

    writeField(FieldID.End, KdbxOuterHeader.end)
}

internal fun commonKdfParameters(param: KdfParameters): ByteString {
    val fields = when (param) {
        is KdfParameters.AES -> mapOf(
            KdfParameters.UUID to Kdbx4Field.Bytes(param.uuid),
            KdfParameters.ROUNDS to Kdbx4Field.UInt64(param.rounds),
            KdfParameters.SALT_OR_SEED to Kdbx4Field.Bytes(param.seed),
        )

        is KdfParameters.Argon2 -> mapOf(
            KdfParameters.UUID to Kdbx4Field.Bytes(param.uuid),
            KdfParameters.SALT_OR_SEED to Kdbx4Field.Bytes(param.salt),
            KdfParameters.PARALLELISM to Kdbx4Field.UInt32(param.parallelism),
            KdfParameters.MEMORY to Kdbx4Field.UInt64(param.memory),
            KdfParameters.ITERATIONS to Kdbx4Field.UInt64(param.iterations),
            KdfParameters.VERSION to Kdbx4Field.UInt32(param.version),
        )
    }

    return commonWriteVarDict(fields)
}

internal fun commonWriteVarDict(input: Map<String, Kdbx4Field>): ByteString {
    val buffer = Buffer()
    buffer.writeShortLe(0x0100)

    for ((key, field) in input) {
        buffer.writeByte(field.type)
        buffer.writeIntLe(key.encodeToByteArray().size)
        buffer.writeUtf8(key)

        when (field) {
            is Kdbx4Field.UInt32 -> {
                buffer.writeIntLe(Int.SIZE_BYTES)
                buffer.writeIntLe(field.rawValue.toInt())
            }
            is Kdbx4Field.UInt64 -> {
                buffer.writeIntLe(Long.SIZE_BYTES)
                buffer.writeLongLe(field.rawValue.toLong())
            }
            is Kdbx4Field.Bool -> {
                buffer.writeIntLe(Byte.SIZE_BYTES)
                buffer.writeByte(if (field.rawValue) 0x1 else 0x0)
            }
            is Kdbx4Field.Int32 -> {
                buffer.writeIntLe(Int.SIZE_BYTES)
                buffer.writeIntLe(field.rawValue)
            }
            is Kdbx4Field.Int64 -> {
                buffer.writeIntLe(Long.SIZE_BYTES)
                buffer.writeLong(field.rawValue)
            }
            is Kdbx4Field.Utf8 -> {
                buffer.writeInt(field.rawValue.encodeToByteArray().size)
                buffer.writeUtf8(field.rawValue)
            }
            is Kdbx4Field.Bytes -> {
                buffer.writeIntLe(field.rawValue.size)
                buffer.write(field.rawValue)
            }
        }
    }

    buffer.writeByte(Type.None)
    return buffer.snapshot()
}

private fun BufferedSink.commonWriteSignature(kdbxSignature: KdbxSignature) {
    write(kdbxSignature.first)
    write(kdbxSignature.second)
}


private fun BufferedSink.commonWriteVersion(kdbxVersion: KdbxVersion) {
    writeShortLe(kdbxVersion.minor.toInt())
    writeShortLe(kdbxVersion.major.toInt())
}

private fun BufferedSink.writeField(fieldID: FieldID, data: ByteString) {
    writeByte(fieldID.ordinal)
    writeIntLe(data.size)
    write(data)
}
