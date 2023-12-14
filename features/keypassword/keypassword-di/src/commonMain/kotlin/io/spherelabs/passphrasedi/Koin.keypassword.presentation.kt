package io.spherelabs.passphrasedi

import io.spherelabs.passphraseimpl.presentation.MasterPasswordMiddleware
import io.spherelabs.passphraseimpl.presentation.MasterPasswordReducer
import org.koin.dsl.module

val keyPasswordFeatureModule = module {
    single { MasterPasswordMiddleware(get(), get(), get()) }
    single { MasterPasswordReducer() }
}
