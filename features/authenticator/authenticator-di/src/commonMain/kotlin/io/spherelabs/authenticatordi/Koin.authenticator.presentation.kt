package io.spherelabs.authenticatordi

import io.spherelabs.authenticatorimpl.presentation.AuthenticatorMiddleware
import io.spherelabs.authenticatorimpl.presentation.AuthenticatorReducer
import org.koin.dsl.module

val authenticatorPresentationModule = module {
    single { AuthenticatorReducer() }
    single { AuthenticatorMiddleware(get(), get()) }
}
