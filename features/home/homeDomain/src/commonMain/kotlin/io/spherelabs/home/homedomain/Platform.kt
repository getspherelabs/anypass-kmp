package io.spherelabs.home.homedomain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform