package io.spherelabs.crypto.tinypass.database.header

import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.model.component.BinaryData
import okio.BufferedSink
import okio.BufferedSource
import okio.ByteString
import okio.ByteString.Companion.toByteString


private object InnerHeaderFieldId {
    const val END_OF_HEADER = 0x00
    const val StreamId = 0x01
    const val StreamKey = 0x02
    const val Binary = 0x03
}

private const val BinaryFlagsSize = 1

data class KdbxInnerHeader(
    val streamCipher: CrsAlgorithm,
    val streamKey: ByteString,
    val binaries: Map<ByteString, BinaryData> = linkedMapOf(),
) {
    fun serialize(sink: BufferedSink) = with(sink) {
        serializeStreamCipher()
        serializeStreamKey()
        serializeBinaries()
        serializeEndOfHeader()
    }

    private fun BufferedSink.serializeStreamCipher() {
        writeByte(STREAM_CIPHER)
        writeIntLe(Int.SIZE_BYTES)
        writeIntLe(streamCipher.ordinal)
    }

    private fun BufferedSink.serializeStreamKey() {
        writeByte(STREAM_KEY)
        writeIntLe(streamKey.size)
        write(streamKey)
    }

    private fun BufferedSink.serializeBinaries() {
        for ((_, binary) in binaries) {
            val data = binary.getContent()
            writeByte(BINARY)
            writeIntLe(data.size + BinaryFlagsSize)
            writeByte(if (binary.memoryProtection) 0x1 else 0x0)
            write(data)
        }
    }

    private fun BufferedSink.serializeEndOfHeader() {
        writeByte(END_OF_HEADER)
        writeIntLe(0)
    }

    companion object {
        const val END_OF_HEADER = 0x00
        const val STREAM_CIPHER = 0x01
        const val STREAM_KEY = 0x02
        const val BINARY = 0x03

        fun of(streamKey: ByteString): KdbxInnerHeader {
            return KdbxInnerHeader(
                streamCipher = CrsAlgorithm.ChaCha20,
                streamKey = streamKey,
                binaries = linkedMapOf(),
            )
        }

        fun deserialize(source: BufferedSource): KdbxInnerHeader {
            val binaries = linkedMapOf<ByteString, BinaryData>()
            var streamCipher: CrsAlgorithm? = null
            var streamKey: ByteString? = null

            while (true) {
                val id = source.readByte()
                val length = source.readIntLe().toLong()

                when (id.toInt()) {
                    END_OF_HEADER -> {
                        source.readByteArray(length)
                        break
                    }
                    STREAM_CIPHER -> {
                        streamCipher = CrsAlgorithm.values()[source.readIntLe()]
                    }
                    STREAM_KEY -> {
                        streamKey = source.readByteString(length)
                    }
                    BINARY -> {
                        val memoryProtection = source.readByte() != 0x0.toByte()
                        val content = source.readByteArray(length - BinaryFlagsSize)
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
    }
}

enum class CrsAlgorithm {
    None,
    ArcFourVariant,
    Salsa20,
    ChaCha20
}



