package io.spherelabs.changepasswordpresentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform