package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase

interface KdbxDecoder {
    fun decode(config: KdbxConfiguration): KdbxDatabase
}
