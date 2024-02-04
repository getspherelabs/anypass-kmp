package io.spherelabs.crypto.tinypass.database.compressor

import okio.Sink
import okio.Source


expect fun ByteArray.toGzipSource(): Source
expect fun ByteArray.toGzipSink(): Sink

