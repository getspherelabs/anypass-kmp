package io.spherelabs.crypto.tinypass.database.header

import okio.*

object VarDict {
    private const val VersionFilter: Short = 0xff00.toShort()

    fun deserialize(data: ByteString): Map<String, Kdbx4Field> {
        val result = mutableMapOf<String, Kdbx4Field>()



        val buffer = Buffer().write(data)
        val source = (buffer as Source).buffer()

        val version = source.readShortLe()

        while (true) {
            val type = source.readByte().toInt()

            if (type == Type.None) break

            val key = source.readKey()

            val valueLength = source.readIntLe()

            result[key] = when (type) {
                Type.UInt32 -> Kdbx4Field.UInt32(source.readIntLe().toUInt())
                Type.UInt64 -> Kdbx4Field.UInt64(source.readLongLe().toULong())
                Type.Bool -> Kdbx4Field.Bool(source.readByte() != 0.toByte())
                Type.Int32 -> Kdbx4Field.Int32(source.readIntLe())
                Type.Int64 -> Kdbx4Field.Int64(source.readLongLe())
                Type.Utf8 -> Kdbx4Field.Utf8(source.readUtf8())
                Type.Bytes -> {
                    Kdbx4Field.Bytes(source.readByteString(valueLength.toLong()))
                }
                else -> throw IllegalArgumentException("Unsupported type: $type")
            }
        }

        return result
    }

    private fun BufferedSource.readKey(): String {
        val lengthOfKey = this.readIntLe()

        return this.readByteString(lengthOfKey.toLong()).utf8()
    }
}
