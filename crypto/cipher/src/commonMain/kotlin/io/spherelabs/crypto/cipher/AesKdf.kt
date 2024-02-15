package io.spherelabs.crypto.cipher

import io.spherelabs.crypto.hash.sha256


object AesKdf {
    fun transformKey(
        key: ByteArray,
        seed: ByteArray,
        rounds: ULong,
    ): ByteArray {
        for (r in 0 until rounds.toLong()) {
            AES(seed)[CipherMode.ECB, CipherPadding.NoPadding].encrypt(key, offset = 0, len = 16)
            AES(seed)[CipherMode.ECB, CipherPadding.NoPadding].encrypt(key, offset = 16, len = 16)
        }
        return key.sha256().also {
            key.clear()
        }
    }
}


fun ByteArray.clear() {
    for (i in indices) this[i] = 0x0
}
