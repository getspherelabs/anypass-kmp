package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import okio.BufferedSink

interface KdbxEncoder {
    fun encode(buffer: BufferedSink, config: KdbxConfiguration): KdbxDatabase
}
