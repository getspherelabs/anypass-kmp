package io.spherelabs.crypto.tinypass.database.core

import io.spherelabs.crypto.cipher.AesKdf
import io.spherelabs.crypto.cipher.Argon2Engine
import io.spherelabs.crypto.cipher.Argon2Kdf
import io.spherelabs.crypto.cipher.clear
import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.hash.sha512
import io.spherelabs.crypto.tinypass.database.common.toArgon2EngineType
import io.spherelabs.crypto.tinypass.database.header.KdfParameters
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.SecureBytes

/**
 * Creating a composite key
 *
 * Altogether, there seem to be three possible sources of key data: a database password, a key file
 * and some abstract key provider (typically a hardware device). Key providers are assumed to implement
 * a challenge-response scheme, the challenge used to produce key data being the contents of the MainSeed header field.
 *
 * The details of key provider implementations differ depending on the product:
 * in KeePass you can only have either a key file or a key provider, in kdbxweb you can have both,
 * and KeePassXC even has provisions for multiple key providers. keepass-rs on the other hand does not support key providers at all.
 *
 * All the various key sources are mashed together into a composite key.
 * Since a database password has a wrong size, it is being hashed with SHA-256 first, resulting in 32 bytes.
 * Then all key sources present are concatenated and hashed with SHA-256.
 *
 * In other words: if the database has only a password, its composite key will be SHA256(SHA256(password)).
 * If there is also a key file, it will be SHA256(SHA256(password) + keyfile).
 * If there is a key provider, its data will come instead (KeePass) or after (KeePassXC and kxdbweb) the keyfile data.
 *
 * https://palant.info/2023/03/29/documenting-keepass-kdbx4-file-format/
 */

internal fun transformKey(header: KdbxOuterHeader, keys: KdbxConfiguration): ByteArray {
    fun compositeKey(configuration: KdbxConfiguration): ByteArray {
        val hash = listOfNotNull(
            SecureBytes.fromPlainText(configuration.passphrase ?: "").raw,
            SecureBytes.fromPlainText(configuration.key ?: "").raw,
        )

        val composite = when {
            hash.isNotEmpty() -> {
                hash.reduce { a, b -> a + b }
            }
            else -> ByteArray(0)
        }
        return composite.sha256().also { composite.clear() }
    }

    return when (header.kdfParameters) {
        is KdfParameters.AES -> {
            with(header.kdfParameters) {
                AesKdf.transformKey(
                    key = compositeKey(keys),
                    seed = seed.toByteArray(),
                    rounds = rounds,
                )
            }
        }
        is KdfParameters.Argon2 -> {
            with(header.kdfParameters) {
                Argon2Kdf.transformKey(
                    type = uuid.toArgon2EngineType(),
                    version = Argon2Engine.Version.from(version),
                    password = compositeKey(keys),
                    salt = salt.toByteArray(),
                    secretKey = key?.toByteArray(),
                    additional = associatedData?.toByteArray(),
                    iterations = iterations,
                    parallelism = parallelism,
                    memory = memory,
                )
            }

        }
    }
}

internal fun masterKey(
    masterSeed: ByteArray,
    outerHeader: KdbxOuterHeader,
    config: KdbxConfiguration,
): ByteArray {
    val transformedKey = transformKey(outerHeader, config)
    return (masterSeed + transformedKey).sha256()
}


internal fun hmacKey(
    masterSeed: ByteArray,
    outerHeader: KdbxOuterHeader,
    config: KdbxConfiguration,
): ByteArray {
    val transformedKey = transformKey(outerHeader, config)
    val hash = masterSeed + transformedKey + 0x01
    return (ByteArray(8) { 0xFF.toByte() } + hash.sha512())
        .sha512()
        .also { hash.clear() }
}

