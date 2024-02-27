package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import okio.Buffer
import okio.Sink

interface KdbxEncoder {
    fun encode(buffer: Buffer, config: KdbxConfiguration): KdbxDatabase
}
