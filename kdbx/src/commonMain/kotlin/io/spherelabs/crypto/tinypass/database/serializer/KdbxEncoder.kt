package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core2.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core2.KdbxDatabase
import okio.BufferedSink

interface KdbxEncoder {
    fun encode(buffer: BufferedSink, config: KdbxConfiguration): KdbxDatabase
}
