package io.spherelabs.crypto.kdbx.database.header



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
}
