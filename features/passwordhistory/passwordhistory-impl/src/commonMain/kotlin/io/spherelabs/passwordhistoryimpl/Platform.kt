package io.spherelabs.passwordhistoryimpl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform