package io.spherelabs.sshkey

import java.security.KeyPair

actual class MultiplatformKeyPair(private val keypair: KeyPair) {
    actual val publicKey: PublicKey = PublicKey(keypair.public)
    actual val privateKey: PrivateKey = PrivateKey(keypair.private)
}
