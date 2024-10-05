package io.spherelabs.crypto.kdbx.database.serializer

import com.benasher44.uuid.uuid4
import io.spherelabs.anycrypto.securerandom.SecureRandom
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.kdbx.database.core.KdbxConfiguration
import io.spherelabs.crypto.kdbx.database.core.KdbxDatabase
import io.spherelabs.crypto.kdbx.database.header.KdbxInnerHeader
import io.spherelabs.crypto.kdbx.database.header.KdbxOuterHeader
import io.spherelabs.crypto.kdbx.database.model.component.KdbxQuery
import io.spherelabs.crypto.kdbx.database.entity.Group
import io.spherelabs.crypto.kdbx.database.model.component.Meta
import io.spherelabs.crypto.kdbx.database.serializer.internal.commonKdbxDecode
import io.spherelabs.crypto.kdbx.database.serializer.internal.commonKdbxEncode
import okio.*
import okio.ByteString.Companion.toByteString

/**
 * A [KdbxSerializer] provides serialization and deserialization functionality for
 * Keepass database files in the Kdbx format.
 *
 * [KdbxSerializer] implements both the [KdbxEncoder] and [KdbxDecoder] interfaces, allowing it
 * to encode [KdbxDatabase] into byte streams and decode byte streams into [KdbxDatabase].
 */

object KdbxSerializer : KdbxEncoder, KdbxDecoder {
    private val random: SecureRandom by lazy { buildSecureRandom() }

    private val outerHeader: KdbxOuterHeader by lazy { KdbxOuterHeader.of(random) }

    private val innerHeader: KdbxInnerHeader by lazy {
        KdbxInnerHeader.of(
            random.nextBytes(64).toByteString(),
        )
    }

    override fun encode(
        buffer: BufferedSink,
        config: KdbxConfiguration,
        query: KdbxQuery,
    ): KdbxDatabase {
        val meta = Meta(
            name = config.path,
        )

        println("Encode in quuery = $query")

        return commonKdbxEncode(
            buffer,
            KdbxDatabase(
                configuration = config,
                outerHeader = outerHeader,
                innerHeader = innerHeader,
                query = query.copy(
                    meta = meta
                )
            ),
        )
    }

    override fun encode(buffer: BufferedSink, config: KdbxConfiguration): KdbxDatabase {
        val meta = Meta(
            name = config.path,
        )

        return commonKdbxEncode(
            buffer,
            KdbxDatabase(
                configuration = config,
                outerHeader = outerHeader,
                innerHeader = innerHeader,
                query = KdbxQuery(
                    meta = meta,
                    group = Group(
                        id = uuid4(),
                        name = config.path,
                        isAutoTyped = true,
                        isSearchable = true,
                    ),
                    deletedObjects = emptyList(),
                ),
            ),
        )
    }

    override fun decode(buffer: BufferedSource, db: KdbxDatabase): KdbxDatabase {
        return commonKdbxDecode(buffer, db)
    }

}
