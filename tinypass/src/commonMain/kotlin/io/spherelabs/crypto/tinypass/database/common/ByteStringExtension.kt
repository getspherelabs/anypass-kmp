package io.spherelabs.crypto.tinypass.database.common

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidOf
import okio.Buffer
import okio.ByteString


internal fun ByteString.toUuid(): Uuid {
    val buffer = Buffer()
    buffer.write(this)

    val data = buffer.readByteArray()

    return uuidOf(data)
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
