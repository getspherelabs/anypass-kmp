package io.spherelabs.onboardingdi

import OnboardingSharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import io.spherelabs.onboardingimpl.ui.OnboardingScreen

val onboardingScreenModule = screenModule {
    register<OnboardingSharedScreen.OnboardingScreen> {
        OnboardingScreen()
    }
}
