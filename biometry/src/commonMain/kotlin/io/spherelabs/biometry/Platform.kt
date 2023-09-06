package io.spherelabs.biometry

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform