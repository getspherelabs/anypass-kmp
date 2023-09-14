package io.spherelabs.lockerkmp.di

import org.koin.dsl.module
import io.spherelabs.features.onboardingpresentation.OnboardingViewModel
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordViewModel
import io.spherelabs.generatepasswordpresentation.GeneratePasswordViewModel
import io.spherelabs.home.homepresentation.HomeViewModel
import org.koin.core.module.dsl.singleOf

actual val viewModelModule = module {
    singleOf(::OnboardingViewModel)
    singleOf(::AddNewPasswordViewModel)
    singleOf(::GeneratePasswordViewModel)
    singleOf(::HomeViewModel)
}