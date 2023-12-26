package io.spherelabs.data.settings.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toBlockingObservableSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import io.spherelabs.data.settings.keypassword.EncryptedSettings
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@OptIn(ExperimentalSettingsImplementation::class, ExperimentalSettingsApi::class)
actual fun platformModule() = module {
    single { androidContext().settingsStore }
    singleOf(::DataStoreSettings) { bind<FlowSettings>() }
    single { get<FlowSettings>().toBlockingObservableSettings() }
    single { EncryptedSettings(androidContext()) }
}

val Context.settingsStore by preferencesDataStore("settings")
