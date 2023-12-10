package io.spherelabs.onboardingdi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform