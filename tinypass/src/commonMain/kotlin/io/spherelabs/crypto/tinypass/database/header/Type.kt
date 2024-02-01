package io.spherelabs.crypto.tinypass.database.header

object Type {
    const val None = 0
    const val UInt32 = 0x04
    const val UInt64 = 0x05
    const val Bool = 0x08
    const val Int32 = 0x0C
    const val Int64 = 0x0D
    const val Utf8 = 0x18
    const val Bytes = 0x42
}
