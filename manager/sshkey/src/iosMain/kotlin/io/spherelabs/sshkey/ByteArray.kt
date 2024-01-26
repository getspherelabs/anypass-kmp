package io.spherelabs.sshkey

actual fun ByteArray.toPrivateKey() = PrivateKey()

actual fun ByteArray.toPublicKey() = PublicKey()
