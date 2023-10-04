package io.spherelabs.resource.strings

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform