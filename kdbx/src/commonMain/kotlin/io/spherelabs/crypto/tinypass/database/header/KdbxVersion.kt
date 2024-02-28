package io.spherelabs.crypto.tinypass.database.header

import okio.BufferedSink
import okio.BufferedSource

/**
 * Outer header starts with signature and version.
 */
data class KdbxVersion(
    val major: Short,
    val minor: Short,
) {

    fun isAtLeast(major: Short, minor: Short): Boolean {
        return this.major > major || (this.major == major && this.minor >= minor)
    }

    companion object {
        fun deserialize(source: BufferedSource): KdbxVersion {
            return KdbxVersion(
                major = source.readShortLe(),
                minor = source.readShortLe(),
            )
        }
    }
}
