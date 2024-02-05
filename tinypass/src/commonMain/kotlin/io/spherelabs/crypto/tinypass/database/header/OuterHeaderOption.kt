package io.spherelabs.crypto.tinypass.database.header

import io.spherelabs.crypto.tinypass.database.signature.Signature
import okio.Buffer
import okio.BufferedSink

data class OuterHeaderOption(
    val signature: Signature,
    val version: Version,
    val cipherId: CipherId,
    val compressionFlags: CompressionFlags
) {
    fun serialize(sink: BufferedSink) {
        signature.serialize(sink)
        version.serialize(sink)

        sink.writeByte(CIPHER_ID)
        sink.writeIntLe(16)
        val bytes = Buffer().apply {
            writeLong(cipherId.uuid.mostSignificantBits)
            writeLong(cipherId.uuid.leastSignificantBits)
        }
        sink.write(bytes.readByteArray())

        sink.writeByte(OuterHeader.COMPRESSION)
        sink.writeIntLe(Int.SIZE_BYTES)
        sink.writeIntLe(compressionFlags.ordinal)
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
