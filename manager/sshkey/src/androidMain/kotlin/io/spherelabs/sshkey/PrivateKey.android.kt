package io.spherelabs.sshkey

import java.security.PrivateKey as JavaPrivateKey

/**
 * It represents a multiplatform wrapper class for a private key in a Java-based cryptographic system.
 *
 * The [PrivateKey] class encapsulates a private key ([java.security.PrivateKey]) and provides a convenient
 * interface for handling private key-related operations. It is part of asymmetric key cryptography,
 * where private keys are used for signing data, decrypting messages, and other cryptographic operations.
 */
actual class PrivateKey(private var privateKey: JavaPrivateKey) {
    actual val encoded: ByteArray = privateKey.encoded

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as PrivateKey
        if (this.privateKey != other.privateKey) return false
        return true
    }

    override fun hashCode(): Int {
        return privateKey.hashCode()
    }
}
