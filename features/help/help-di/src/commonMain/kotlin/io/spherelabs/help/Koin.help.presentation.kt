package io.spherelabs.help

import io.spherelabs.help.presentation.HelpMiddleware
import io.spherelabs.help.presentation.HelpReducer
import org.koin.dsl.module

val helpPresentationModule = module {
    single { HelpReducer() }
    single { HelpMiddleware(get()) }
}
