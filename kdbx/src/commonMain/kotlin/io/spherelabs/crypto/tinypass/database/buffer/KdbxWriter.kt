package io.spherelabs.crypto.tinypass.database.buffer


import okio.BufferedSink

/**
 * A [KdbxWriter] designed for managing the writing process of KDBX databases,
 * including the outer header, inner header, and encrypted data components.
 *
 * This interface provides methods for writing the outer and inner headers of a KDBX database,
 * along with encrypting and storing sensitive data. It uses [BufferedSink] to improve performance
 * and make the writing process smoother. Buffered sinks are powerful that can apply various
 * transformations to data streams, like compression, encryption, and protocol framing.
 *
 * Additionally, they should consider scalability and extensibility to accommodate
 * potential future enhancements or customizations in the writing process.
 *
 */

interface KdbxWriter {
    fun write(sink: BufferedSink, strategy: WriterStrategy)
}
