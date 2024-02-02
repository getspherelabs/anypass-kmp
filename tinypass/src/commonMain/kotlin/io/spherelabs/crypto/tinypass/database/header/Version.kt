package io.spherelabs.crypto.tinypass.database.header

import okio.BufferedSink
import okio.BufferedSource

data class Version(
    val major: Short,
    val minor: Short,
) {
    fun isAtLeast(major: Short, minor: Short): Boolean {
        return this.major > major || (this.major == major && this.minor >= minor)
    }

    internal fun writeTo(sink: BufferedSink) = with(sink) {
        writeShortLe(minor.toInt())
        writeShortLe(major.toInt())
    }

    companion object {
        internal fun readFrom(source: BufferedSource) = Version(
            minor = source.readShortLe(),
            major = source.readShortLe(),
        )
    }
}
