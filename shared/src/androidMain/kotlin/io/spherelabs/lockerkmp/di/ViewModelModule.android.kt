package io.spherelabs.lockerkmp.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import io.spherelabs.features.onboardingpresentation.OnboardingViewModel
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordViewModel
import io.spherelabs.generatepasswordpresentation.GeneratePasswordViewModel

actual val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::AddNewPasswordViewModel)
    viewModelOf(::GeneratePasswordViewModel)
}