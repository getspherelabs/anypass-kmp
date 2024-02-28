package io.spherelabs.crypto.tinypass.database.header

import io.spherelabs.crypto.tinypass.database.signature.KdbxSignature
import okio.Buffer
import okio.BufferedSink
import okio.buffer

data class OuterHeaderOption(
    var kdbxSignature: KdbxSignature,
    var kdbxVersion: KdbxVersion,
    var cipherId: CipherId,
    var compressionFlags: CompressionFlags,
) {
    fun serialize(sink: BufferedSink) = with(sink) {
        kdbxSignature.serialize(sink)
        //kdbxVersion.serialize(sink)
        writeByte(FieldID.CipherID.ordinal)
        writeIntLe(CIPHER_ID_LENGTH)
        val bytes = Buffer().apply {
            writeLong(cipherId.uuid.mostSignificantBits)
            writeLong(cipherId.uuid.leastSignificantBits)
        }.readByteString()

        write(bytes)

        writeByte(FieldID.CompressionFlags.ordinal)
        writeIntLe(Int.SIZE_BYTES)
        writeIntLe(compressionFlags.ordinal)
    }

    companion object {
        val Default = OuterHeaderOption(
            kdbxSignature = KdbxSignature.Default,
            kdbxVersion = KdbxVersion(4, 1),
            compressionFlags = CompressionFlags.GZip,
            cipherId = CipherId.Aes,
        )

        const val CIPHER_ID = 1
        const val COMPRESSION = 2

        const val CIPHER_ID_LENGTH = 16
    }
}
