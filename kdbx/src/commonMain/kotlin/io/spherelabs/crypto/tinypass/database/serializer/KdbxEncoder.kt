package io.spherelabs.crypto.tinypass.database.serializer

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import okio.BufferedSink

/**
 * A [KdbxEncoder] represents an encoder responsible for encoding a [KdbxDatabase]
 * into a byte stream using a specified configuration.
 *
 * The [BufferedSink] represents a destination for writing bytes, while the [KdbxConfigurationb]
 * provides the necessary configuration for the encoding process.
 */

interface KdbxEncoder {
    fun encode(buffer: BufferedSink, config: KdbxConfiguration): KdbxDatabase
}
