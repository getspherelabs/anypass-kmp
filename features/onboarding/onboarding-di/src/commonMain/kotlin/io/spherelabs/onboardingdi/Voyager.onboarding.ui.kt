package io.spherelabs.onboardingdi

import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.navigationapi.OnboardingDestination
import io.spherelabs.onboardingimpl.ui.OnboardingScreen

val onboardingScreenModule = screenModule {
    register<OnboardingDestination.Onboarding> {
        OnboardingScreen()
    }
}
