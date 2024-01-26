package io.spherelabs.sshkey

import java.security.PrivateKey as JavaPrivateKey

actual class PrivateKey(var privateKey: JavaPrivateKey) {
    actual val encoded: ByteArray = byteArrayOf()
}
