package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import okio.BufferedSource

/**
 * A [KdbxReader] designed for managing for reading headers from a buffered source.
 *
 * To access header information stored within a KDBX database file.
 *
 */
interface KdbxReader {
    fun readInnerHeader(source: BufferedSource): KdbxInnerHeader
    fun readOuterHeader(source: BufferedSource): KdbxOuterHeader
}
