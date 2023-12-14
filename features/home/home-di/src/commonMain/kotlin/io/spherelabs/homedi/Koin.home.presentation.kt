package io.spherelabs.homedi

import io.spherelabs.homeimpl.presentation.HomeMiddleware
import io.spherelabs.homeimpl.presentation.HomeReducer
import org.koin.dsl.module


val homePresentationModule = module {
    single { HomeReducer() }
    single { HomeMiddleware(get(), get()) }
}
