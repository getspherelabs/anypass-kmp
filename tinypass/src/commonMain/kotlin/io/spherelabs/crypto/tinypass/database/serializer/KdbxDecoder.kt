package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import okio.BufferedSource

interface KdbxDecoder {
    fun decode(source: BufferedSource, config: KdbxConfiguration): KdbxDatabase
}
