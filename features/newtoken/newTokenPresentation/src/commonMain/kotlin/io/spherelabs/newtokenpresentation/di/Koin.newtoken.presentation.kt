package io.spherelabs.newtokenpresentation.di

import io.spherelabs.newtokenpresentation.NewTokenMiddleware
import org.koin.dsl.module

val newTokenPresentationModule = module {
    single { NewTokenMiddleware(get()) }
}
