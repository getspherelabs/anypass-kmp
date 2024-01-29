package io.spherelabs.sshkey

import java.security.PublicKey as JavaPublicKey
import java.security.KeyFactory
import java.security.KeyPair
import java.security.spec.X509EncodedKeySpec

/**
 * It represents a multiplatform wrapper class for a public key in a Java-based cryptographic system.
 *
 * The [PublicKey] class encapsulates a Java public key ([java.security.PublicKey]) and provides a convenient
 * interface for handling public key-related operations. It is part of asymmetric key cryptography,
 * where public keys are used for encrypting messages, verifying digital signatures, and other cryptographic operations.
 */
actual class PublicKey(private var publicKey: JavaPublicKey) {
    actual val encoded: ByteArray = publicKey.encoded

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as PublicKey
        if (this.publicKey != other.publicKey) return false
        return true
    }

    override fun hashCode(): Int {
        return publicKey.hashCode()
    }
}
