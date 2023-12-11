package io.spherelabs.anypass.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.spherelabs.accountdomain.repository.di.accountDomainModule
import io.spherelabs.accountpresentation.di.accountPresentationModule
import io.spherelabs.addnewpasswodpresentation.di.addNewPasswordFeatureModule
import io.spherelabs.addnewpassworddomain.di.addNewPasswordUseCaseDomainModule
import io.spherelabs.admob.di.admobModule
import io.spherelabs.authdi.authDomainModule
import io.spherelabs.authdi.authFeatureModule
import io.spherelabs.changepassworddomain.di.changePasswordDomainModule
import io.spherelabs.changepasswordpresentation.di.changePasswordPresentationModule
import io.spherelabs.data.local.di.localModule
import io.spherelabs.data.settings.di.settingModule
import io.spherelabs.firebase.di.firebaseAuthModule
import io.spherelabs.generatepassworddomain.di.generatePasswordUseCaseDomainModule
import io.spherelabs.generatepasswordpresentation.di.generatePasswordPresentationModule
import io.spherelabs.homedi.homeDomainModule
import io.spherelabs.homedi.homePresentationModule
import io.spherelabs.manager.password.di.passwordManagerModule
import io.spherelabs.newtokendi.domain.newTokenDomainModule
import io.spherelabs.newtokendi.presentation.newTokenPresentationModule
import io.spherelabs.onboardingdi.onboardingDomainModule
import io.spherelabs.onboardingdi.onboardingFeatureModule
import io.spherelabs.passphrasedi.keyPasswordDomainModule
import io.spherelabs.passphrasedi.keyPasswordFeatureModule
import io.spherelabs.validation.di.validationModule
import org.koin.compose.LocalKoinScope
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope
import org.koin.dsl.KoinAppDeclaration

expect fun platformModule(): Module

fun initKoin(declaration: KoinAppDeclaration = {}) =
    startKoin {
        declaration()

        modules(
            firebaseAuthModule,
            admobModule,
            settingModule,
            localModule,
            validationModule,
            passwordManagerModule,
            onboardingDomainModule,
            onboardingFeatureModule,
            addNewPasswordUseCaseDomainModule,
            addNewPasswordFeatureModule,
            generatePasswordUseCaseDomainModule,
            generatePasswordPresentationModule,
            homeDomainModule,
            homePresentationModule,
            authDomainModule,
            authFeatureModule,
            accountDomainModule,
            accountPresentationModule,
            changePasswordDomainModule,
            changePasswordPresentationModule,
            newTokenDomainModule,
            newTokenPresentationModule,
            keyPasswordDomainModule,
            keyPasswordFeatureModule,
            viewModelModule,
        )
    }

fun initKoin() = initKoin {}

@Composable
inline fun <reified T> useInject(
    qualifier: Qualifier? = null,
    scope: Scope = LocalKoinScope.current,
    noinline parameters: ParametersDefinition? = null,
): T {
    return remember(qualifier, scope, parameters) {
        scope.get(qualifier, parameters)
    }
}
