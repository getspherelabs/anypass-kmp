package io.spherelabs.lockerkmp.di

import io.spherelabs.data.settings.di.settingModule
import io.spherelabs.features.onboardingpresentation.di.onboardingFeatureModule
import io.spherelabs.onboardingdomain.di.onboardingDomainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(declaration: KoinAppDeclaration = {}) =
    startKoin {
        declaration()

        modules(
            settingModule,
            onboardingDomainModule,
            onboardingFeatureModule,
            viewModelModule
        )
    }

fun initKoin() = initKoin {}