package io.spherelabs.crypto.uuid


/**
 * UUID stands for Universally Unique Identifier
 * UUIDs are 36 character strings containing numbers, letters and dashes.
 * UUIDs are designed to be globally unique.
 * UUIDs are written in 5 groups of hexadecimal digits separated by hyphens.
 * The length of each group is: 8-4-4-4-12. UUIDs are fixed length.
 */
interface Uuid {
    public val mostSignificantBits: Long
    public val leastSignificantBits: Long
    public fun isZero(): Boolean
    public fun of(mostSigBits: Long, leastSigBits: Long): Uuid
}

expect fun uuid4(): Uuid

