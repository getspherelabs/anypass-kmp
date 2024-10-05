package io.spherelabs.crypto.tinypass.database

import io.spherelabs.crypto.cipher.ChaCha7539Engine
import io.spherelabs.crypto.cipher.Salsa20Engine
import io.spherelabs.crypto.hash.sha256
import io.spherelabs.crypto.hash.sha512
import io.spherelabs.crypto.tinypass.database.header.CrsAlgorithm
import okio.ByteString

private val SalsaNonce = intArrayOf(0xe8, 0x30, 0x09, 0x4b, 0x97, 0x20, 0x5d, 0x2a)
    .map(Int::toByte)
    .toByteArray()

/**
 * Used to encrypt/decrypt values marked with 'Protected' flag
 * during XML content encoding/decoding.
 */
sealed class EncryptionSaltGenerator {
    /**
     * Get salt using underlying algorithm and advance the counter.
     */
    abstract fun getSalt(length: Int): ByteArray

    /**
     * Encrypt/decrypt [input] with salt supplied by underlying
     * algorithm and advance the counter.
     */
    abstract fun processBytes(input: ByteArray): ByteArray

    class Salsa20(key: ByteArray) : EncryptionSaltGenerator() {
        private val engine = Salsa20Engine().apply {
            init(key.sha256(), SalsaNonce)
        }

        override fun getSalt(length: Int) = engine.getBytes(length)

        override fun processBytes(input: ByteArray) = engine.processBytes(input)
    }

    class ChaCha20(key: ByteArray) : EncryptionSaltGenerator() {
        private val engine = ChaCha7539Engine().apply {
            val hash = key.sha512()
            init(
                key = hash.sliceArray(0 until 32),
                iv = hash.sliceArray(32 until 44),
            )
        }

        override fun getSalt(length: Int) = engine.getBytes(length)

        override fun processBytes(input: ByteArray) = engine.processBytes(input)
    }

    companion object {
        fun create(id: CrsAlgorithm, key: ByteString) = when (id) {
            CrsAlgorithm.Salsa20 -> Salsa20(key.toByteArray())
            CrsAlgorithm.ChaCha20 -> ChaCha20(key.toByteArray())
            else -> throw Exception("Unsupported inner random stream cipher.")
        }
    }
}
