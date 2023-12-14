package io.spherelabs.onboardingdi

import io.spherelabs.onboardingimpl.presentation.OnboardingMiddleware
import io.spherelabs.onboardingimpl.presentation.OnboardingReducer
import org.koin.dsl.module

val onboardingFeatureModule = module {
    factory { OnboardingReducer() }
    factory { OnboardingMiddleware(get(), get(), get()) }
}
