package io.spherelabs.changepassworddi

import io.spherelabs.changepasswordimpl.presentation.ChangePasswordMiddleware
import io.spherelabs.changepasswordimpl.presentation.ChangePasswordReducer
import io.spherelabs.changepasswordimpl.presentation.ChangePasswordValidateMiddleware
import org.koin.dsl.module

val changePasswordPresentationModule = module {
    single { ChangePasswordValidateMiddleware(get()) }
    single { ChangePasswordMiddleware(get(), get()) }
    single { ChangePasswordReducer() }
}
