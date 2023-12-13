package io.spherelabs.authenticatornavigation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform