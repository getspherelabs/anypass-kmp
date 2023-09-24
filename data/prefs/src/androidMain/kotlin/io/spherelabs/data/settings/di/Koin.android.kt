package io.spherelabs.data.settings.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toBlockingObservableSettings
import org.koin.android.ext.koin.androidContext
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module

@OptIn(ExperimentalSettingsImplementation::class, ExperimentalSettingsApi::class)
actual fun platformModule() = module {
    single { androidContext().settingsStore }
    singleOf(::DataStoreSettings) { bind<FlowSettings>() }
    single { get<FlowSettings>().toBlockingObservableSettings() }
}

val Context.settingsStore by preferencesDataStore("settings")