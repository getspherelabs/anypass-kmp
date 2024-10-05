package io.spherelabs.crypto.tinypass.database.compressor

import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.toCValues
import platform.darwin.COMPRESSION_ZLIB
import platform.darwin.compression_decode_buffer
import platform.darwin.compression_encode_buffer
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Source

/**
 * gzip-decompresses this [Source] while reading.
 */
@OptIn(
    ExperimentalForeignApi::class, ExperimentalEncodingApi::class,
    ExperimentalUnsignedTypes::class,
)
actual fun ByteArray.ungzip(): ByteArray {
    val capacity = 10_000_000
    return memScoped {
        val input = Base64.Default.decode(this@ungzip).also { println(it.size) }

        val destinationBuffer = allocArray<UByteVar>(capacity)
        val oldSize = compression_decode_buffer(
            destinationBuffer, capacity.convert(),
            input.toUByteArray().toCValues(), input.size.convert(),
            null,
            COMPRESSION_ZLIB,
        )
        destinationBuffer.readBytes(oldSize.convert())
    }
}


@OptIn(ExperimentalForeignApi::class)
actual fun ByteArray.gzip(): ByteArray {
    val capacity = 10_000_000
    return memScoped {
        val input = this@gzip
        val destinationBuffer = allocArray<UByteVar>(capacity)

        val newSize = compression_encode_buffer(
            destinationBuffer, capacity.convert(),
            input.toUByteArray().toCValues(), input.size.convert(),
            null,
            COMPRESSION_ZLIB,
        )
        destinationBuffer.readBytes(newSize.convert())
    }
}
