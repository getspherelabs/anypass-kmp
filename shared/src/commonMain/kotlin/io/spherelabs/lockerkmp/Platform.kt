package io.spherelabs.lockerkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform