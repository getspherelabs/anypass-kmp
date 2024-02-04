package io.spherelabs.crypto.tinypass.database.signature

import okio.BufferedSink
import okio.BufferedSource
import okio.ByteString

/**
 * Outer header starts with signature and version.
 * Represents the header of .kdb and .kdbx file formats, consisting of two 4-byte fields for file signatures.
 *
 * @property first The first 4-byte signature. It will always have a value of 0x9AA2D903.
 * @property second The second 4-byte signature. It can have (for now) 3 different value.
 */

data class Signature(
    val first: ByteString,
    val second: ByteString,
) {

    fun serialize(sink: BufferedSink) = serializeInternal(sink)

    private fun serializeInternal(sink: BufferedSink) = with(sink) {
        write(first)
        write(second)
    }

    companion object {
        val FirstSignature = ByteString.of(0x03, 0xd9.toByte(), 0xa2.toByte(), 0x9a.toByte())
        val SecondSignature = ByteString.of(0x67, 0xfb.toByte(), 0x4b, 0xb5.toByte())
        val Default = Signature(FirstSignature, SecondSignature)

        fun deserialize(source: BufferedSource) = Signature(
            first = source.readByteString(4),
            second = source.readByteString(4),
        )
    }
}
