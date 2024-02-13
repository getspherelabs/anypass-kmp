package io.spherelabs.crypto.tinypass.database.common

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.bytes
import com.benasher44.uuid.uuidOf
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

internal fun Uuid.toBase64(): String {
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
