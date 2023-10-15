package io.spherelabs.mlkit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform