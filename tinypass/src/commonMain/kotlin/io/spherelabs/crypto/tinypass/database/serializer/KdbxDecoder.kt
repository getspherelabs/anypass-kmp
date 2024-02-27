package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import okio.Buffer
import okio.BufferedSource

interface KdbxDecoder {
    fun decode(buffer: Buffer, db: KdbxDatabase): KdbxDatabase
    fun decode(config: KdbxConfiguration): KdbxDatabase
}
