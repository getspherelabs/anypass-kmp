package io.spherelabs.newtokenpresentation.di

import io.spherelabs.newtokenpresentation.NewTokenMiddleware
import io.spherelabs.newtokenpresentation.NewTokenReducer
import org.koin.dsl.module

val newTokenPresentationModule = module {
    single { NewTokenMiddleware(get()) }
    single { NewTokenReducer() }
}
