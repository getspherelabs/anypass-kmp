package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core2.KdbxDatabase
import okio.BufferedSource

interface KdbxDecoder {
    fun decode(buffer: BufferedSource, db: KdbxDatabase): KdbxDatabase
}
