package io.spherelabs.data.local.di

import io.spherelabs.data.local.db.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single { DriverFactory(get()) }
    }
}