package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.buffer.internal.*
import io.spherelabs.crypto.tinypass.database.buffer.internal.commonReadOuterHeader
import io.spherelabs.crypto.tinypass.database.buffer.internal.commonWriteOuterHeader
import io.spherelabs.crypto.tinypass.database.header.*
import io.spherelabs.crypto.tinypass.database.model.component.Content
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource

/**
 * Buffer is a mutable sequence of bytes. Like ArrayList, you don’t need to size your buffer in advance.
 * You read and write buffers as a queue: write data to the end and read it from the front.
 * There’s no obligation to manage positions, limits, or capacities.
 */

object KdbxBuffer : KdbxWriter, KdbxReader {
    val buffer = Buffer()

    override fun write(header: KdbxOuterHeader, bytes: ByteArray, key: ByteArray) {
        val seed = header.seed.toByteArray()
        TODO()
    }

    override fun writeInnerHeader(sink: BufferedSink, innerHeader: KdbxInnerHeader) =
        sink.commonWriteInnerHeader(innerHeader)

    override fun readOuterHeader(): KdbxOuterHeader = buffer.commonReadOuterHeader()

    override fun readOuterHeader(bufferedSource: BufferedSource): KdbxOuterHeader {
        return bufferedSource.commonReadOuterHeader()
    }

    override fun readInnerHeader(bufferedSource: BufferedSource): KdbxInnerHeader {
        return bufferedSource.commonReadInnerHeader()
    }

    override fun readInnerHeader(): KdbxInnerHeader = buffer.commonReadInnerHeader()

    override fun writeOuterHeader(outerHeader: KdbxOuterHeader) =
        buffer.commonWriteOuterHeader(outerHeader)


    override fun writeOuterHeader(sink: BufferedSink, outerHeader: KdbxOuterHeader) =
        sink.commonWriteOuterHeader(outerHeader)

    override fun writeInnerHeader(innerHeader: KdbxInnerHeader) =
        buffer.commonWriteInnerHeader(innerHeader)

}
