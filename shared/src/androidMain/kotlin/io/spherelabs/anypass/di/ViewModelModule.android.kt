package io.spherelabs.anypass.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import io.spherelabs.features.onboardingpresentation.OnboardingViewModel
import io.spherelabs.addnewpasswodpresentation.AddNewPasswordViewModel
import io.spherelabs.generatepasswordpresentation.GeneratePasswordViewModel
import io.spherelabs.home.homepresentation.HomeViewModel
import io.spherelabs.authpresentation.signup.SignUpViewModel
import io.spherelabs.authpresentation.signin.SignInViewModel
import io.spherelabs.masterpasswordpresentation.MasterPasswordViewModel
import io.spherelabs.accountpresentation.AccountViewModel
import io.spherelabs.changepasswordpresentation.ChangePasswordViewModel
import io.spherelabs.newtokenpresentation.NewTokenViewModel

actual val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::AddNewPasswordViewModel)
    viewModelOf(::GeneratePasswordViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::MasterPasswordViewModel)
    viewModelOf(::AccountViewModel)
    viewModelOf(::ChangePasswordViewModel)
    viewModelOf(::NewTokenViewModel)
}
