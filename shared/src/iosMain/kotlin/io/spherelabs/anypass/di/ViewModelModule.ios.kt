package io.spherelabs.anypass.di

import org.koin.dsl.module
import org.koin.core.module.dsl.factoryOf
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
import io.spherelabs.anypass.app.SharedViewModel
import  io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryViewModel

actual val viewModelModule = module {
    factoryOf(::OnboardingViewModel)
    factoryOf(::AddNewPasswordViewModel)
    factoryOf(::GeneratePasswordViewModel)
    factoryOf(::HomeViewModel)
    factoryOf(::SignInViewModel)
    factoryOf(::SignUpViewModel)
    factoryOf(::MasterPasswordViewModel)
    factoryOf(::AccountViewModel)
    factoryOf(::ChangePasswordViewModel)
    factoryOf(::NewTokenViewModel)
    factoryOf(::AuthenticatorViewModel)
    factoryOf(::PasswordHealthViewModel)
    factoryOf(::HelpViewModel)
    factoryOf(::SharedViewModel)
    factoryOf(::PasswordHistoryViewModel)
}
