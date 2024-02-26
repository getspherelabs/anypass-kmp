package io.spherelabs.crypto.tinypass.database.serializer

import com.benasher44.uuid.uuid4
import io.spherelabs.anycrypto.securerandom.buildSecureRandom
import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.header.KdfParameters
import io.spherelabs.crypto.tinypass.database.model.component.KdbxQuery
import io.spherelabs.crypto.tinypass.database.model.component.Group
import io.spherelabs.crypto.tinypass.database.model.component.Meta
import io.spherelabs.crypto.tinypass.database.serializer.internal.commonKdbxEncode
import okio.ByteString.Companion.toByteString

object KdbxSerializer : KdbxEncoder, KdbxDecoder {

    override fun encode(config: KdbxConfiguration): KdbxDatabase {
        val random = buildSecureRandom()
        val outerHeader = KdbxOuterHeader.create().copy(
            seed = random.nextBytes(32).toByteString(),
            encryptionIV = random.nextBytes(16).toByteString(),
            kdfParameters = KdfParameters.Argon2(
                uuid = KdfParameters.KdfArgon2d,
                salt = random.nextBytes(32).toByteString(),
                parallelism = 2U,
                memory = 32UL * 1024UL * 1024UL,
                iterations = 8U,
                version = 0x13.toUInt(),
                key = null,
                associatedData = null,
            ),
            customData = linkedMapOf(),
        )
        val innerHeader = KdbxInnerHeader.of(
            random.nextBytes(64).toByteString(),
        )
        val meta = Meta()

        return commonKdbxEncode(
            KdbxDatabase(
                configuration = config,
                outerHeader = outerHeader,
                innerHeader = innerHeader,
                query = KdbxQuery(
                    meta = meta,
                    group = Group(
                        id = uuid4(),
                        title = config.path,
                        isAutoTyped = true,
                        isSearchable = true,
                    ),
                    deletedObjects = emptyList(),
                ),
            ),
        )

    }

    override fun decode(config: KdbxConfiguration): KdbxDatabase {
        TODO("Not yet implemented")
    }
}
