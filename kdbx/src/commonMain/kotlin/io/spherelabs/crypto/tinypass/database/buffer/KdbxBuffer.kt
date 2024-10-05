package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.buffer.internal.*
import io.spherelabs.crypto.tinypass.database.buffer.internal.commonReadOuterHeader
import io.spherelabs.crypto.tinypass.database.buffer.internal.commonWriteOuterHeader
import io.spherelabs.crypto.tinypass.database.header.*
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource

/**
 * A [KdbxBuffer] provides functionality to read and write data buffers.
 *
 * As an implementation of both KdbxWriter and KdbxReader interfaces, KdbxBuffer seamlessly integrates
 * with buffered sources and sinks, facilitating the reading and writing of headers within the KeePass
 * database (KDBX) format.
 *
 * ### FYI:
 *
 * Buffer is a mutable sequence of bytes. Like ArrayList, you don’t need to size your buffer in advance.
 * You read and write buffers as a queue: write data to the end and read it from the front.
 * There’s no obligation to manage positions, limits, or capacities.
 */
object KdbxBuffer : KdbxWriter, KdbxReader {

    override fun readOuterHeader(source: BufferedSource): KdbxOuterHeader {
        return try {
            source.commonReadOuterHeader()
        } catch (e: Exception) {
            throw Exception("Outer header is empty.")
        }

    }

    override fun readInnerHeader(source: BufferedSource): KdbxInnerHeader {
        return source.commonReadInnerHeader()
    }

    override fun write(sink: BufferedSink, strategy: WriterStrategy) {
        when (strategy) {
            is WriterStrategy.Inner -> sink.commonWriteInnerHeader(strategy.innerHeader)
            is WriterStrategy.Outer -> sink.commonWriteOuterHeader(strategy.outerHeader)
        }
    }
}
