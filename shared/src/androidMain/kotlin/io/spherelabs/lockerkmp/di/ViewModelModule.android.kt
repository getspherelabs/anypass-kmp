package io.spherelabs.lockerkmp.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import io.spherelabs.features.onboardingpresentation.OnboardingViewModel

actual val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
}