package io.spherelabs.accountdi

import io.spherelabs.designsystem.url.BrowserNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module { single { BrowserNavigator(androidContext()) } }
