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
    fun serialize(sink: BufferedSink)  = with(sink){
        signature.serialize(sink)
        version.serialize(sink)

        writeByte(CIPHER_ID)
        writeIntLe(16)
        val bytes = Buffer().apply {
            writeLong(cipherId.uuid.mostSignificantBits)
            writeLong(cipherId.uuid.leastSignificantBits)
        }
        write(bytes.readByteArray())

        writeByte(OuterHeader.COMPRESSION)
        writeIntLe(Int.SIZE_BYTES)
        writeIntLe(compressionFlags.ordinal)
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
