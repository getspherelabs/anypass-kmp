package io.spherelabs.home.homepresentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform