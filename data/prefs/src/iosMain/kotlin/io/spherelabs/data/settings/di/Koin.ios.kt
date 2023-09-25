package io.spherelabs.data.settings.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class)
actual fun platformModule() = module {
  single<ObservableSettings> { NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults) }
  single { get<ObservableSettings>().toFlowSettings() }
}
