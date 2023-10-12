package io.spherelabs.changepassworddomain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform