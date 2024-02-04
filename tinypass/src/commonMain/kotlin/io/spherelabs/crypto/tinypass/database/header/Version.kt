package io.spherelabs.crypto.tinypass.database.header

import okio.BufferedSink
import okio.BufferedSource

/**
 * Outer header starts with signature and version.
 */
data class Version(
    val major: Short,
    val minor: Short,
) {
    fun serialize(sink: BufferedSink) {
        sink.writeShortLe(minor.toInt())
        sink.writeShortLe(major.toInt())
    }

    fun isAtLeast(major: Short, minor: Short): Boolean {
        return this.major > major || (this.major == major && this.minor >= minor)
    }

    companion object {
        fun deserialize(source: BufferedSource): Version {
            return Version(
                major = source.readShortLe(),
                minor = source.readShortLe(),
            )
        }
    }
}
