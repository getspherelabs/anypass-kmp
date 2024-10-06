package io.spherelabs.crypto.rsa

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform