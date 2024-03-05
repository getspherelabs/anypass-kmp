package io.spherelabs.sshkey

import java.security.KeyPair

/**
 * It represents a multiplatform wrapper class for a key pair in a cryptographic system.
 *
 * The [MultiplatformKeyPair] class encapsulates a platform-independent [KeyPair] and provides a convenient
 * interface for handling key pair-related operations. It is designed to work across multiple platforms
 * and serves as a common abstraction for dealing with both public and private keys.
 *
 */
actual class MultiplatformKeyPair(private val keypair: KeyPair) {
    actual val publicKey: PublicKey get() = PublicKey(keypair.public)
    actual val privateKey: PrivateKey get() = PrivateKey(keypair.private)
}
