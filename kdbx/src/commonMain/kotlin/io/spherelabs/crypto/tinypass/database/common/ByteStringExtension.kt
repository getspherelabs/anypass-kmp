package io.spherelabs.crypto.tinypass.database.common

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.bytes
import com.benasher44.uuid.uuidOf
import kotlin.experimental.inv
import kotlin.experimental.xor
import okio.Buffer
import okio.ByteString


internal fun ByteString.toUuid(): Uuid? {
    val buffer = Buffer()
    buffer.write(this)

    val data = buffer.readByteArray()

    return if (data.isEmpty()) null else {
        uuidOf(data)
    }
}



fun Uuid.toBase64(): String {
    val buffer = Buffer()
    buffer.write(this.bytes)

    return buffer.readByteString().base64()
}

internal fun ByteString.toIntLe(): Int {
    val buffer = Buffer()
    buffer.write(this)

    var number = 0

    repeat(this.size) { i ->
        val bitIndex = i * 8
        number = buffer.readByte().toInt() and 0xff shl bitIndex or number

    }

    return number
}

internal fun ByteString.toLongLe(): Long {
    val buffer = Buffer()
    buffer.write(this)

    var number = 0L

    repeat(this.size) { i ->
        val bitIndex = i * 8
        number = buffer.readByte().toLong() and 0xff shl bitIndex or number
    }

    return number
}


private const val HexMap = "0123456789abcdef"

internal fun ByteArray.encodeHex(): String {
    val result = StringBuilder(size * 2)
    for (byte in this) {
        val n = byte.toInt()
        result.append(HexMap[n shr 4 and 0x0f])
        result.append(HexMap[n and 0x0f])
    }

    return result.toString()
}

internal fun String.decodeHexToArray(): ByteArray {
    require(length % 2 == 0) { "Invalid hex string length." }

    val result = ByteArray(length / 2)
    for (i in result.indices) {
        val d1 = decodeHexDigit(this[i * 2]) shl 4
        val d2 = decodeHexDigit(this[i * 2 + 1])
        result[i] = (d1 + d2).toByte()
    }

    return result
}

private fun decodeHexDigit(c: Char) = when (c) {
    in '0'..'9' -> c - '0'
    in 'a'..'f' -> c - 'a' + 10
    in 'A'..'F' -> c - 'A' + 10
    else -> throw IllegalArgumentException("Unexpected hex char: $c")
}

internal fun littleEndianToInt(bs: ByteArray, offset: Int): Int {
    var off = offset
    var n: Int = bs[off].toInt() and 0xff
    n = n or (bs[++off].toInt() and 0xff shl 8)
    n = n or (bs[++off].toInt() and 0xff shl 16)
    n = n or (bs[++off].toInt() shl 24)
    return n
}

internal fun littleEndianToInt(bs: ByteArray, bOff: Int, ns: IntArray, nOff: Int, count: Int) {
    var byteArrayOffset = bOff
    for (i in 0 until count) {
        ns[nOff + i] = littleEndianToInt(bs, byteArrayOffset)
        byteArrayOffset += 4
    }
}

internal fun littleEndianToInt(bs: ByteArray, off: Int, count: Int): IntArray {
    var offset = off
    val ns = IntArray(count)
    for (i in ns.indices) {
        ns[i] = littleEndianToInt(bs, offset)
        offset += 4
    }
    return ns
}

internal fun littleEndianToLong(bs: ByteArray, off: Int, ns: LongArray) {
    var offset = off
    for (i in ns.indices) {
        ns[i] = littleEndianToLong(bs, offset)
        offset += 8
    }
}

internal fun littleEndianToLong(bs: ByteArray, off: Int): Long {
    val lo = littleEndianToInt(bs, off)
    val hi = littleEndianToInt(bs, off + 4)
    return (hi.toLong() and 0xffffffffL) shl 32 or (lo.toLong() and 0xffffffffL)
}

internal fun intToLittleEndian(n: Int) = ByteArray(4).apply {
    intToLittleEndian(n, this, 0)
}

internal fun intToLittleEndian(n: Int, bs: ByteArray, off: Int) {
    bs[off] = n.toByte()
    bs[off + 1] = (n ushr 8).toByte()
    bs[off + 2] = (n ushr 16).toByte()
    bs[off + 3] = (n ushr 24).toByte()
}

internal fun intToLittleEndian(ns: IntArray, bs: ByteArray, off: Int) {
    var offset = off
    for (i in ns.indices) {
        intToLittleEndian(ns[i], bs, offset)
        offset += 4
    }
}

internal fun longToLittleEndian(n: Long) = ByteArray(8).apply {
    longToLittleEndian(n, this, 0)
}

internal fun longToLittleEndian(ns: LongArray, bs: ByteArray, off: Int) {
    var offset = off
    for (i in ns.indices) {
        longToLittleEndian(ns[i], bs, offset)
        offset += 8
    }
}

internal fun longToLittleEndian(n: Long, bs: ByteArray, off: Int) {
    intToLittleEndian((n and 0xffffffffL).toInt(), bs, off)
    intToLittleEndian((n ushr 32).toInt(), bs, off + 4)
}

internal fun ByteArray.constantTimeEquals(other: ByteArray): Boolean {
    if (this === other) {
        return true
    }
    val length = if (other.size < this.size) other.size else this.size
    var notEqual = other.size xor this.size
    for (i in 0 until length) {
        notEqual = notEqual or (other[i] xor this[i]).toInt()
    }
    for (i in length until this.size) {
        notEqual = notEqual or (this[i] xor this[i].inv()).toInt()
    }
    return notEqual == 0
}
