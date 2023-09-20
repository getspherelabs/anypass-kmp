package io.spherelabs.firebase.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val firebaseAuthModule = module {
    includes(platformModule())
}