package io.spherelabs.crypto.tinypass.database.header

import okio.ByteString


sealed class Kdbx4Field(
    override val type: Int,
) : Field {
    data class UInt32(val rawValue: UInt) : Kdbx4Field(Type.UInt32)
    data class UInt64(val rawValue: ULong) : Kdbx4Field(Type.UInt64)
    data class Bool(val rawValue: Boolean) : Kdbx4Field(Type.Bool)
    data class Int32(val rawValue: Int) : Kdbx4Field(Type.Int32)
    data class Int64(val rawValue: Long) : Kdbx4Field(Type.Int64)
    data class Utf8(val rawValue: String) : Kdbx4Field(Type.Utf8)
    data class Bytes(val rawValue: ByteString) : Kdbx4Field(Type.Bytes)
}
