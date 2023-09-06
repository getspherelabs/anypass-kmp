package io.spherelabs.data.settings

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform