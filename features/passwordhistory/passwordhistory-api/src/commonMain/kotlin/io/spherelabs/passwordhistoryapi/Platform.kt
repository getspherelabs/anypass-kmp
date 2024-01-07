package io.spherelabs.passwordhistoryapi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform