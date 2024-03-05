package io.spherelabs.crypto.cipher

object Argon2Kdf {
    fun transformKey(
        type: Argon2Engine.Type,
        version: Argon2Engine.Version,
        password: ByteArray,
        secretKey: ByteArray?,
        additional: ByteArray?,
        salt: ByteArray,
        iterations: ULong,
        parallelism: UInt,
        memory: ULong
    ): ByteArray {
        val result = ByteArray(32)
        Argon2Engine(
            type = type,
            salt = salt,
            secret = secretKey,
            additional = additional,
            iterations = iterations.toInt(),
            parallelism = parallelism.toInt(),
            memory = memory.toInt() / 1024,
            version = version
        ).encrypt(password, result)

        return result
    }
}
