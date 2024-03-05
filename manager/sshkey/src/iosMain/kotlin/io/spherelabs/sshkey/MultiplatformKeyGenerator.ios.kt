package io.spherelabs.sshkey

actual class MultiplatformKeyGenerator {
    actual fun generate(): MultiplatformKeyPair {
        return MultiplatformKeyPair()
    }
}
