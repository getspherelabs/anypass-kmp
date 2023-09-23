package io.spherelabs.analytics

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform