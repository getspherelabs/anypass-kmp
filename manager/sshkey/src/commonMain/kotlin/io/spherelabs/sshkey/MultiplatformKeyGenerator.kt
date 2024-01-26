package io.spherelabs.sshkey

expect class MultiplatformKeyGenerator() {
    fun generate(): MultiplatformKeyPair
}

object KeyGenerator {
    val instance = MultiplatformKeyGenerator()
}
