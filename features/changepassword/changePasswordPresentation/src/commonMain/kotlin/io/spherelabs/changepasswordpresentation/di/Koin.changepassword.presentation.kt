package io.spherelabs.changepasswordpresentation.di

import io.spherelabs.changepasswordpresentation.ChangePasswordMiddleware
import io.spherelabs.changepasswordpresentation.ChangePasswordReducer
import io.spherelabs.changepasswordpresentation.ChangePasswordValidateMiddleware
import org.koin.dsl.module

val changePasswordPresentationModule = module {
    single { ChangePasswordValidateMiddleware(get()) }
    single { ChangePasswordMiddleware(get(), get()) }
    single { ChangePasswordReducer() }
}
