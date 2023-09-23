package io.spherelabs.anypass.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import io.spherelabs.features.onboardingpresentation.OnboardingViewModel
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordViewModel
import io.spherelabs.generatepasswordpresentation.GeneratePasswordViewModel
import io.spherelabs.home.homepresentation.HomeViewModel
import io.spherelabs.authpresentation.signup.SignUpViewModel
import io.spherelabs.authpresentation.signin.SignInViewModel

actual val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::AddNewPasswordViewModel)
    viewModelOf(::GeneratePasswordViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
}