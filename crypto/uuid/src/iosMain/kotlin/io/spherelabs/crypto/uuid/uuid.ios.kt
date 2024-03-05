package io.spherelabs.crypto.uuid

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.posix.O_RDONLY
import platform.posix.close
import platform.posix.errno
import platform.posix.open
import platform.posix.read


internal class IosUuid(
    private val bytes: ByteArray,
) : Uuid {

    private val uuidByteRanges: List<IntRange> = listOf(
        0 until 4,
        4 until 6,
        6 until 8,
        8 until 10,
        10 until 16,
    )

    override var mostSignificantBits: Long = bytes.bits(0, 8)

    override var leastSignificantBits: Long = bytes.bits(8, 16)

    override fun toString(): String {
        val characters = CharArray(36)
        var charIndex = 0
        for (range in uuidByteRanges) {
            for (i in range) {
                val octetPair = bytes[i]
                // convert the octet pair in this byte into 2 characters
                val left = octetPair.toInt().shr(4) and 0b00001111
                val right = octetPair.toInt() and 0b00001111
                characters[charIndex++] = UUID_CHARS[left]
                characters[charIndex++] = UUID_CHARS[right]
            }
            if (charIndex < 36) {
                characters[charIndex++] = '-'
            }
        }
        return characters.concatToString()
    }

    override fun isZero(): Boolean {
        return leastSignificantBits == 0L && mostSignificantBits == 0L
    }

    override fun of(mostSigBits: Long, leastSigBits: Long): Uuid {
        mostSignificantBits = mostSigBits
        leastSignificantBits = leastSigBits
        return IosUuid(getRandomUuidBytes())
    }

    private fun halfByteFromChar(char: Char) = when (char) {
        in '0'..'9' -> char.code - 48
        in 'a'..'f' -> char.code - 87
        in 'A'..'F' -> char.code - 55
        else -> null
    }


    override fun fromString(name: String): Uuid {
        var msb: Long = 0
        var lsb: Long = 0
        for (i in 0..7) msb = msb shl 8 or (name[i].code and 0xff).toLong()
        for (i in 8..15) lsb = lsb shl 8 or (name[i].code and 0xff).toLong()
        val bytes = ByteArray(16)
        var byte = 0
        for (range in UUID_CHAR_RANGES) {
            var i = range.first
            while (i < range.last) {
                // Collect each pair of UUID chars and their int representations
                val left = halfByteFromChar(name[i++])
                val right = halfByteFromChar(name[i++])
                require(left != null && right != null) {
                    "Uuid string has invalid characters: $name"
                }

                // smash them together into a single byte
                bytes[byte++] = (left.shl(4) or right).toByte()
            }
        }
        return IosUuid(bytes)
    }
}

@SharedImmutable
internal val UUID_CHAR_RANGES: List<IntRange> = listOf(
    0 until 8,
    9 until 13,
    14 until 18,
    19 until 23,
    24 until 36,
)

@SharedImmutable
internal val UUID_CHARS = ('0'..'9') + ('a'..'f')


actual fun uuid4(): Uuid = IosUuid(getRandomUuidBytes())


private fun ByteArray.bits(start: Int, end: Int): Long {
    var b = 0L
    var i = start
    do {
        b = (b shl 8) or (get(i).toLong() and 0xff)
    } while (++i < end)
    return b
}


@OptIn(ExperimentalForeignApi::class)
internal fun getRandomUuidBytes(): ByteArray {
    return bytesWithURandomFd { fd, bytePtr ->
        read(fd, bytePtr, 16.toULong())
    }
}




@OptIn(ExperimentalForeignApi::class)
internal fun bytesWithURandomFd(fdLambda: (Int, CPointer<ByteVar>) -> Unit): ByteArray {
    return ByteArray(16).also { bytes ->
        val fd = open("/dev/urandom", O_RDONLY)
        check(fd != -1) { "Failed to access /dev/urandom: $errno" }
        try {
            bytes.usePinned {
                fdLambda(fd, it.addressOf(0))
            }
        } finally {
            close(fd)
        }
    }
}

