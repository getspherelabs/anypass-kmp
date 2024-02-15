package io.spherelabs.crypto.tinypass.database.header

import io.spherelabs.crypto.tinypass.database.signature.Signature
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource
import okio.buffer

data class OuterHeaderOption(
    var signature: Signature,
    var version: Version,
    var cipherId: CipherId,
    var compressionFlags: CompressionFlags,
) {
    fun serialize(sink: BufferedSink) = with(sink) {
        signature.serialize(sink)
        version.serialize(sink)
        println("Sink is ${sink.buffer()}")
        writeByte(FieldID.CipherID.ordinal)
        writeIntLe(CIPHER_ID_LENGTH)
        val bytes = Buffer().apply {
            writeLong(cipherId.uuid.mostSignificantBits)
            writeLong(cipherId.uuid.leastSignificantBits)
        }.readByteString()

        write(bytes)

        println("Option bytes are $bytes")
        println("Id is ${FieldID.CompressionFlags.ordinal}")
        writeByte(FieldID.CompressionFlags.ordinal)
        writeIntLe(Int.SIZE_BYTES)
        writeIntLe(compressionFlags.ordinal)

        println("Sink is ${sink.buffer()}")
    }

    companion object {
        val Default = OuterHeaderOption(
            signature = Signature.Default,
            version = Version(4, 1),
            compressionFlags = CompressionFlags.GZip,
            cipherId = CipherId.Aes,
        )

        const val CIPHER_ID = 1
        const val COMPRESSION = 2

        const val CIPHER_ID_LENGTH = 16
    }
}
