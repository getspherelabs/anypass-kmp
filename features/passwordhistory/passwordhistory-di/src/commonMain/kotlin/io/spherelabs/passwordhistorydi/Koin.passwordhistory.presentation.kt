package io.spherelabs.passwordhistorydi

import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryMiddleware
import io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryReducer
import org.koin.dsl.module

val passwordHistoryPresentationModule = module {
    single { PasswordHistoryReducer() }
    single { PasswordHistoryMiddleware(get(), get()) }
}
