package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import okio.BufferedSink

object KdbxSink {
    fun write(sink: BufferedSink, component: KdbxOutput) {
        when (component) {
            is KdbxOutput.InnerHeader -> writeInnerHeader(
                header = component.innerHeader,
                sink = sink,
            )
            is KdbxOutput.OuterHeader -> {

            }
        }
    }

    private fun writeInnerHeader(header: KdbxInnerHeader, sink: BufferedSink) = with(sink) {
        writeByte(KdbxConstants.STREAM_CIPHER)
        writeIntLe(Int.SIZE_BYTES)
        writeIntLe(header.streamCipher.ordinal)

        writeByte(KdbxConstants.STREAM_KEY)
        writeIntLe(header.streamKey.size)
        write(header.streamKey)

        for ((_, binary) in header.binaries) {
            val data = binary.getContent()
            writeByte(KdbxConstants.BINARY)
            writeIntLe(data.size + 1)
            writeByte(if (binary.memoryProtection) 0x1 else 0x0)
            write(data)
        }

        writeByte(KdbxConstants.END_OF_HEADER)
        writeIntLe(0)
    }

    private fun writeOuterHeader(header: KdbxOuterHeader, sink: BufferedSink) {

    }
}
