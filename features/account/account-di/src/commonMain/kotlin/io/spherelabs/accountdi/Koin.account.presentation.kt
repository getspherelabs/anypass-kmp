package io.spherelabs.accountdi

import io.spherelabs.accountimpl.presentation.AccountMiddleware
import io.spherelabs.accountimpl.presentation.AccountReducer
import org.koin.dsl.module

val accountPresentationModule = module {
    single { AccountReducer() }
    single {
        AccountMiddleware(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}
