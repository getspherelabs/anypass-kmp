package io.spherelabs.lockerkmp.di

import io.spherelabs.addnewpasswodpresentation.di.addNewPasswordFeatureModule
import io.spherelabs.addnewpassworddomain.di.addNewPasswordDomainModule
import io.spherelabs.authdomain.di.authDomainModule
import io.spherelabs.authpresentation.authFeatureModule
import io.spherelabs.data.local.di.localModule
import io.spherelabs.data.settings.di.settingModule
import io.spherelabs.features.onboardingpresentation.di.onboardingFeatureModule
import io.spherelabs.firebase.di.firebaseAuthModule
import io.spherelabs.generatepassworddomain.di.generatePasswordDomainModule
import io.spherelabs.generatepasswordpresentation.di.generatePasswordPresentationModule
import io.spherelabs.home.homedomain.di.homeDomainModule
import io.spherelabs.home.homepresentation.di.homePresentationModule
import io.spherelabs.manager.password.di.passwordManagerModule
import io.spherelabs.onboardingdomain.di.onboardingDomainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(declaration: KoinAppDeclaration = {}) =
    startKoin {
        declaration()

        modules(
            firebaseAuthModule,
            settingModule,
            localModule,
            passwordManagerModule,
            onboardingDomainModule,
            onboardingFeatureModule,
            addNewPasswordDomainModule,
            addNewPasswordFeatureModule,
            generatePasswordDomainModule,
            generatePasswordPresentationModule,
            homeDomainModule,
            homePresentationModule,
            authDomainModule,
            authFeatureModule,
            viewModelModule
        )
    }

fun initKoin() = initKoin {}