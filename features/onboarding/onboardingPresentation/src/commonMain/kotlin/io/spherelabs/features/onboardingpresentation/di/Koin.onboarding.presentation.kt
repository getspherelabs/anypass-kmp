package io.spherelabs.features.onboardingpresentation.di

import io.spherelabs.features.onboardingpresentation.OnboardingMiddleware
import io.spherelabs.features.onboardingpresentation.OnboardingReducer
import org.koin.dsl.module

val onboardingFeatureModule = module {
    factory { OnboardingReducer() }
    factory{ OnboardingMiddleware(get(), get()) }
}