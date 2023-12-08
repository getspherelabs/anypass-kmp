package io.spherelabs.onboardingnavigation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform