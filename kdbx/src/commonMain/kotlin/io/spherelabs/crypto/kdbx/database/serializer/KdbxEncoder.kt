package io.spherelabs.crypto.kdbx.database.serializer

import io.spherelabs.crypto.kdbx.database.core.KdbxConfiguration
import io.spherelabs.crypto.kdbx.database.core.KdbxDatabase
import io.spherelabs.crypto.kdbx.database.model.component.KdbxQuery
import okio.BufferedSink

/**
 * A [KdbxEncoder] represents an encoder responsible for encoding a [KdbxDatabase]
 * into a byte stream using a specified configuration.
 *
 * The [BufferedSink] represents a destination for writing bytes, while the [KdbxConfiguration]
 * provides the necessary configuration for the encoding process.
 */

interface KdbxEncoder {
    fun encode(buffer: BufferedSink, config: KdbxConfiguration): KdbxDatabase
    fun encode(buffer: BufferedSink, config: KdbxConfiguration, query: KdbxQuery): KdbxDatabase
}
