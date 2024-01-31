package io.spherelabs.crypto.cipher

/**
 * https://github.com/korlibs/korge/blob/main/korlibs-crypto/src/korlibs/internal/InternalCryptoArrays.kt
 */
// NEED TO TEST
/** Copies [size] elements of [src] starting at [srcPos] into [dst] at [dstPos]  */
internal fun arraycopy(src: ByteArray, srcPos: Int, dst: ByteArray, dstPos: Int, size: Int) {
    src.copyInto(dst, dstPos, srcPos, srcPos + size)
}


internal inline infix fun Int.leftRotate(bitCount: Int): Int {
    return (this shl bitCount) or (this ushr (32 - bitCount))
}

@Suppress("NOTHING_TO_INLINE") // Pending `kotlin.experimental.xor` becoming stable
internal inline infix fun Byte.xor(other: Byte): Byte = (toInt() xor other.toInt()).toByte()

/** Copies [size] elements of [src] starting at [srcPos] into [dst] at [dstPos]  */
internal fun arraycopy(src: IntArray, srcPos: Int, dst: IntArray, dstPos: Int, size: Int) {
    src.copyInto(dst, dstPos, srcPos, srcPos + size)
}

internal fun arraycopy(src: LongArray, srcPos: Int, dst: LongArray, dstPos: Int, size: Int) {
    src.copyInto(dst, dstPos, srcPos, srcPos + size)
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

fun Int.rotateLeft(distance: Int): Int {
    return this shl distance or (this ushr -distance)
}
