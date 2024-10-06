package io.spherelabs.sshkey

expect fun ByteArray.toPrivateKey(): PrivateKey
expect fun ByteArray.toPublicKey(): PublicKey
