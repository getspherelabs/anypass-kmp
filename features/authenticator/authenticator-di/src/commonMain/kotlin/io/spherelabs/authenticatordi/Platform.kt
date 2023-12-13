package io.spherelabs.authenticatordi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform