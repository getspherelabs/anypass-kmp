package io.spherelabs.authenticatorpresentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform