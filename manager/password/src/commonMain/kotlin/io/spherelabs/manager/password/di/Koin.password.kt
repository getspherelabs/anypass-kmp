package io.spherelabs.manager.password.di

import io.spherelabs.manager.password.*
import org.koin.dsl.module

val passwordManagerModule = module {
    single { DigitRandom() }
    single { LowercaseRandom() }
    single { UppercaseRandom() }
    single { SpecialRandom() }
    single<PasswordManager> { DefaultPasswordManager(get(), get(), get(), get()) }
}