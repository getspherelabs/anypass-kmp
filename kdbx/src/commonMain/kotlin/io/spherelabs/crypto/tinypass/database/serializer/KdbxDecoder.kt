package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import okio.BufferedSource

/**
 * A [KdbxDecoder] represents a decoder responsible for decoding byte streams into [KdbxDatabase].
 */
interface KdbxDecoder {
    fun decode(buffer: BufferedSource, db: KdbxDatabase): KdbxDatabase
}
