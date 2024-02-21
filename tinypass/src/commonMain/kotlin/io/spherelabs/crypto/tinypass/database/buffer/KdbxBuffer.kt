package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.common.toIntLe
import io.spherelabs.crypto.tinypass.database.common.toUuid
import io.spherelabs.crypto.tinypass.database.header.*
import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource
import okio.ByteString

object KdbxBuffer : KdbxWriter, KdbxReader {
    private val buffer = Buffer()

    override fun readOuterHeader(): KdbxOuterHeader = buffer.commonReadOuterHeader()

    override fun readInnerHeader(): KdbxInnerHeader {
        TODO("Not yet implemented")
    }

    override fun readSignature(): KdbxSignature = buffer.commonReadSignature()

    override fun writeOuterHeader(outerHeader: KdbxOuterHeader) =
        buffer.commonWriteOuterHeader(outerHeader)

    override fun writeOuterHeaderOption(option: OuterHeaderOption) {
        TODO("Not yet implemented")
    }

    override fun writeInnerHeader(innerHeader: KdbxInnerHeader) =
        buffer.commonWriteInnerHeader(innerHeader)

    override fun writeSignature(signature: KdbxSignature) = buffer.commonWriteSignature(signature)

    override fun writeVersion(kdbxVersion: KdbxVersion) = buffer.commonWriteVersion(kdbxVersion)

    /**
     * TIP: I used the [with]` function effectively to improve readability and reduce redundancy.
     * This is a good practice.
     */
    private fun BufferedSink.commonWriteInnerHeader(header: KdbxInnerHeader) {
        writeByte(KdbxConstants.STREAM_CIPHER)
        writeIntLe(Int.SIZE_BYTES)
        writeIntLe(header.streamCipher.ordinal)

        writeByte(KdbxConstants.STREAM_KEY)
        writeIntLe(header.streamKey.size)
        write(header.streamKey)

        for ((_, binary) in header.binaries) {
            val data = binary.getContent()
            writeByte(KdbxConstants.BINARY)
            writeIntLe(data.size + 1)
            writeByte(if (binary.memoryProtection) 0x1 else 0x0)
            write(data)
        }

        writeByte(KdbxConstants.END_OF_HEADER)
        writeIntLe(0)
    }

    private fun BufferedSink.commonWriteOuterHeader(header: KdbxOuterHeader) {
        header.option.serialize(this)
        writeField(FieldID.Seed, header.seed) // Write the seed and size of seed.
        writeField(FieldID.EncryptionIV, header.encryptionIV)

        val params = header.kdfParameters.serialize()
        writeField(FieldID.KdfParameters, params)

        val customData = VarDict.serialize(header.customData)
        writeField(FieldID.PublicCustomData, customData)

        writeField(FieldID.End, KdbxOuterHeader.end)
    }

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
    private fun BufferedSource.commonReadOuterHeader(): KdbxOuterHeader {
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


    private fun BufferedSink.commonWriteSignature(kdbxSignature: KdbxSignature) {
        write(kdbxSignature.first)
        write(kdbxSignature.second)
    }

    private fun BufferedSink.commonWriteVersion(kdbxVersion: KdbxVersion) {
        writeShortLe(kdbxVersion.minor.toInt())
        writeShortLe(kdbxVersion.major.toInt())
    }

    private fun BufferedSource.commonReadSignature(): KdbxSignature {
        return KdbxSignature(
            first = readByteString(4),
            second = readByteString(4),
        )
    }

    private fun BufferedSink.writeField(fieldID: FieldID, data: ByteString) {
        writeByte(fieldID.ordinal)
        writeIntLe(data.size)
        write(data)
    }

    private fun BufferedSource.commonReadId(
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
}
