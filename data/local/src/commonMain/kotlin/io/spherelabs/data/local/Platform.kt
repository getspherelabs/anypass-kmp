package io.spherelabs.data.local

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform