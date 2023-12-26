package io.spherelabs.anypass.di

import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
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
    singleOf(::OnboardingViewModel)
    singleOf(::AddNewPasswordViewModel)
    singleOf(::GeneratePasswordViewModel)
    singleOf(::HomeViewModel)
    singleOf(::SignInViewModel)
    singleOf(::SignUpViewModel)
    singleOf(::MasterPasswordViewModel)
    singleOf(::AccountViewModel)
    singleOf(::ChangePasswordViewModel)
    singleOf(::NewTokenViewModel)
    singleOf(::AuthenticatorViewModel)
    singleOf(::PasswordHealthViewModel)
    singleOf(::HelpViewModel)
}
