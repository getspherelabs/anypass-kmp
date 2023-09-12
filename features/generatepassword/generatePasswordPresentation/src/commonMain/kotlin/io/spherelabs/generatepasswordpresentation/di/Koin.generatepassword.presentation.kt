package io.spherelabs.generatepasswordpresentation.di

import io.spherelabs.generatepasswordpresentation.GeneratePasswordMiddleware
import io.spherelabs.generatepasswordpresentation.GeneratePasswordReducer
import org.koin.dsl.module

val generatePasswordPresentationModule = module {
    single { GeneratePasswordReducer() }
    single { GeneratePasswordMiddleware(get()) }
}