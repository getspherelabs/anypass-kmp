package io.spherelabs.designsystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform