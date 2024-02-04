package io.spherelabs.crypto.tinypass.database.compressor


import okio.Buffer
import okio.Sink
import okio.Source
import okio.gzip


actual fun ByteArray.toGzipSource(): Source {
    val buffer = Buffer().write(this)
    return (buffer as Source).gzip()
}

actual fun ByteArray.toGzipSink(): Sink {
    val buffer = Buffer().write(this)
    return (buffer as Sink).gzip()
}
