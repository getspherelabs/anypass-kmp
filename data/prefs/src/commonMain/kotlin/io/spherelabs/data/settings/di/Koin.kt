package io.spherelabs.data.settings.di

import io.spherelabs.changepasswordapi.domain.repository.ChangePasswordRepository
import io.spherelabs.data.settings.fingerprint.DefaultFingerPrintSetting
import io.spherelabs.data.settings.fingerprint.FingerPrintSetting
import io.spherelabs.data.settings.keypassword.DefaultMasterPasswordSetting
import io.spherelabs.data.settings.keypassword.MasterPasswordSetting
import io.spherelabs.data.settings.onboarding.DefaultOnboardingSetting
import io.spherelabs.data.settings.onboarding.OnboardingSetting
import io.spherelabs.data.settings.repository.DefaultChangePasswordRepository
import io.spherelabs.data.settings.screenshot.DefaultRestrictScreenshotSettings
import io.spherelabs.data.settings.screenshot.RestrictScreenshotSettings
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val settingModule = module {
    includes(platformModule())
    factory<OnboardingSetting> { DefaultOnboardingSetting(get()) }
    factory<MasterPasswordSetting> { DefaultMasterPasswordSetting(get()) }
    factory<FingerPrintSetting> { DefaultFingerPrintSetting(get()) }
    single<ChangePasswordRepository> { DefaultChangePasswordRepository(get()) }
    single<RestrictScreenshotSettings> { DefaultRestrictScreenshotSettings(get()) }
}
