package io.spherelabs.crypto.tinypass.database.buffer

import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader

/**
 * A [WriterStrategy] representing different strategies for writing headers in a KDBX database.
 *
 * It provides a flexible and type-safe way to define various strategies for writing
 * both outer and inner headers during the database serialization process.
 *
 * - [Outer]: Represents a strategy for writing the outer header of the KDBX database.
 * - [Inner]: Represents a strategy for writing the inner header of the KDBX database.
 *
 */

sealed class WriterStrategy {
    data class Outer(val outerHeader: KdbxOuterHeader) : WriterStrategy()
    data class Inner(val innerHeader: KdbxInnerHeader) : WriterStrategy()
}
