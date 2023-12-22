package io.spherelabs.passwordhealthdi

import org.koin.dsl.module
import passwordhealthimpl.presentation.PasswordHealthMiddleware
import passwordhealthimpl.presentation.PasswordHealthReducer

val passwordHealthPresentationModule = module {
    single { PasswordHealthReducer() }
    single { PasswordHealthMiddleware(get(), get(), get()) }
}
