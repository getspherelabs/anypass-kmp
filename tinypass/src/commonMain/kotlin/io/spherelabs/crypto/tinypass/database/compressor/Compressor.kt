package io.spherelabs.crypto.tinypass.database.compressor

import okio.Buffer
import okio.Sink
import okio.Source


expect fun ByteArray.toGzipSource(): Source
expect fun Buffer.toGzipSink(data: ByteArray): ByteArray
