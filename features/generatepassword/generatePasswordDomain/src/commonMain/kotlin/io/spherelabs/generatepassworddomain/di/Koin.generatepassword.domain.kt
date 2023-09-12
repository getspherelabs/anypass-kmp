package io.spherelabs.generatepassworddomain.di

import io.spherelabs.generatepassworddomain.DefaultGeneratePassword
import io.spherelabs.generatepassworddomain.GeneratePassword
import org.koin.dsl.module

val generatePasswordDomainModule = module {
    single<GeneratePassword> { DefaultGeneratePassword(get()) }
}