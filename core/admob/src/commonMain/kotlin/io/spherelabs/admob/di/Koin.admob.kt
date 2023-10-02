package io.spherelabs.admob.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val admobModule = module {
    includes(platformModule())
}
