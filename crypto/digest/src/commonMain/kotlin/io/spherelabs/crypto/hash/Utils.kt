package io.spherelabs.crypto.hash


@Suppress("NOTHING_TO_INLINE") // Syntactic sugar.
internal inline infix fun Byte.and(other: Int): Int = toInt() and other


internal inline infix fun Long.rightRotate(bitCount: Int): Long {
    return (this ushr bitCount) or (this shl (64 - bitCount))
}
