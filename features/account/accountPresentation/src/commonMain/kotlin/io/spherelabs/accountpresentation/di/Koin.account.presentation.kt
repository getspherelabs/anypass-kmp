package io.spherelabs.accountpresentation.di

import io.spherelabs.accountpresentation.AccountMiddleware
import io.spherelabs.accountpresentation.AccountReducer
import org.koin.dsl.module

val accountPresentationModule = module {
    single { AccountReducer() }
    single { AccountMiddleware(get(), get(), get(), get(), get(), get(), get()) }
}
