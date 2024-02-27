package io.spherelabs.crypto.tinypass.database.compressor


import okio.*


actual fun ByteArray.toGzipSource(): Source {
    val buf = Buffer()
    val source = GzipSource(buf)
    source.read(buf, buf.size)

    return buf
}


actual fun Buffer.toGzipSink(data: ByteArray): ByteArray {
    this.write(data)
    val sink = Buffer()
    val gzipSink = GzipSink(sink)
    gzipSink.write(buffer, buffer.size)
    return this.readByteArray()
}
