package io.spherelabs.data.settings.di

import com.russhwolf.settings.ExperimentalSettingsApi
import io.spherelabs.data.settings.onboarding.DefaultOnboardingSetting
import io.spherelabs.data.settings.onboarding.OnboardingSetting
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

@OptIn(ExperimentalSettingsApi::class)
val settingModule = module {
    includes(platformModule())
    factory<OnboardingSetting> { DefaultOnboardingSetting(get()) }
}