package io.spherelabs.anypass.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import io.spherelabs.onboardingimpl.presentation.OnboardingViewModel
import io.spherelabs.addnewpasswordimpl.presentation.AddNewPasswordViewModel
import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordViewModel
import io.spherelabs.homeimpl.presentation.HomeViewModel
import io.spherelabs.authimpl.presentation.signup.SignUpViewModel
import io.spherelabs.authimpl.presentation.signin.SignInViewModel
import io.spherelabs.accountimpl.presentation.AccountViewModel
import io.spherelabs.authenticatorimpl.presentation.AuthenticatorViewModel
import io.spherelabs.changepasswordimpl.presentation.ChangePasswordViewModel
import io.spherelabs.newtokenimpl.presentation.NewTokenViewModel
import io.spherelabs.passphraseimpl.presentation.MasterPasswordViewModel
import passwordhealthimpl.presentation.PasswordHealthViewModel
import io.spherelabs.help.presentation.HelpViewModel

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
    viewModelOf(::AuthenticatorViewModel)
    viewModelOf(::PasswordHealthViewModel)
    viewModelOf(::HelpViewModel)
}
