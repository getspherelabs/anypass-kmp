package io.spherelabs.crypto.tinypass.database.header

import okio.*

object FieldStorage {
    private const val Version: Short = 0x0100
    private const val VersionFilter: Short = 0xff00.toShort()

    fun read(data: ByteString): Map<String, Kdbx4Field> {
        val result = mutableMapOf<String, Kdbx4Field>()

        /**
         * Buffer is a mutable sequence of bytes. Like ArrayList, you don’t need to size your buffer in advance.
         * You read and write buffers as a queue: write data to the end and read it from the front.
         * There’s no obligation to manage positions, limits, or capacities.
         */

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

    fun write(input: Map<String, Kdbx4Field>): ByteString {
        val buffer = Buffer()
        buffer.writeShortLe(Version.toInt())

        for ((key, field) in input) {
            buffer.writeByte(field.type)
            buffer.writeIntLe(key.encodeToByteArray().size)
            buffer.writeUtf8(key)

            when (field) {
                is Kdbx4Field.UInt32 -> {
                    buffer.writeIntLe(Int.SIZE_BYTES)
                    buffer.writeIntLe(field.rawValue.toInt())
                }
                is Kdbx4Field.UInt64 -> {
                    buffer.writeIntLe(Long.SIZE_BYTES)
                    buffer.writeLongLe(field.rawValue.toLong())
                }
                is Kdbx4Field.Bool -> {
                    buffer.writeIntLe(Byte.SIZE_BYTES)
                    buffer.writeByte(if (field.rawValue) 0x1 else 0x0)
                }
                is Kdbx4Field.Int32 -> {
                    buffer.writeIntLe(Int.SIZE_BYTES)
                    buffer.writeIntLe(field.rawValue)
                }
                is Kdbx4Field.Int64 -> {
                    buffer.writeIntLe(Long.SIZE_BYTES)
                    buffer.writeLong(field.rawValue)
                }
                is Kdbx4Field.Utf8 -> {
                    buffer.writeInt(field.rawValue.encodeToByteArray().size)
                    buffer.writeUtf8(field.rawValue)
                }
                is Kdbx4Field.Bytes -> {
                    buffer.writeIntLe(field.rawValue.size)
                    buffer.write(field.rawValue)
                }
            }
        }

        buffer.writeByte(Type.None)
        return buffer.snapshot()
    }

    private fun BufferedSource.readKey(): String {
        val lengthOfKey = this.readIntLe()

        return this.readByteString(lengthOfKey.toLong()).utf8()
    }
}
