package io.spherelabs.anypass.di

import io.spherelabs.designsystem.url.BrowserNavigator
import org.koin.dsl.module

actual fun platformModule() = module {
    single { BrowserNavigator() }
}
