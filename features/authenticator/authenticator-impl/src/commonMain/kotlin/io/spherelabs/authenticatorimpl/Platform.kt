package io.spherelabs.authenticatorimpl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform