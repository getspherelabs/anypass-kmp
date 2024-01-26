package io.spherelabs.sshkey

import java.security.PublicKey as JavaPublicKey

actual class PublicKey(var publicKey: JavaPublicKey) {
    actual val encoded: ByteArray = publicKey.encoded
}
