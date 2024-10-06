package io.spherelabs.crypto.kdbx.database.serializer

import io.spherelabs.crypto.kdbx.database.core.KdbxDatabase
import okio.BufferedSource

/**
 * A [KdbxDecoder] represents a decoder responsible for decoding byte streams into [KdbxDatabase].
 */
interface KdbxDecoder {
    fun decode(buffer: BufferedSource, db: KdbxDatabase): KdbxDatabase
}
