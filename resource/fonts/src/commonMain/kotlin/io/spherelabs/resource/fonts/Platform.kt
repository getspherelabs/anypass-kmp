package io.spherelabs.resource.fonts

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform