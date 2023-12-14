package io.spherelabs.newtokendi

import io.spherelabs.newtokenimpl.presentation.NewTokenMiddleware
import io.spherelabs.newtokenimpl.presentation.NewTokenReducer
import org.koin.dsl.module

val newTokenPresentationModule = module {
    single { NewTokenMiddleware(get()) }
    single { NewTokenReducer() }
}
