package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import okio.BufferedSink


interface KdbxWriter {
    fun write(
        header: KdbxOuterHeader,
        bytes: ByteArray,
        key: ByteArray
    )
    fun writeOuterHeader(outerHeader: KdbxOuterHeader)
    fun writeOuterHeader(sink: BufferedSink, outerHeader: KdbxOuterHeader)
    fun writeInnerHeader(innerHeader: KdbxInnerHeader)
    fun writeInnerHeader(sink: BufferedSink, innerHeader: KdbxInnerHeader)
}
