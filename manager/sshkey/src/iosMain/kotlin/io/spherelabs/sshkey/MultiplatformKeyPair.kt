package io.spherelabs.sshkey

actual class MultiplatformKeyPair {
    actual val privateKey: PrivateKey = PrivateKey()
    actual val publicKey: PublicKey = PublicKey()
}
