package io.spherelabs.anypass.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import io.spherelabs.accountdi.accountUiModule
import io.spherelabs.addnewpassworddi.addNewPasswordUiModule
import io.spherelabs.authdi.authScreenModule
import io.spherelabs.authenticatordi.authenticatorUiModule
import io.spherelabs.changepassworddi.changePasswordUiModule
import io.spherelabs.generatepassworddi.generatePasswordUiModule
import io.spherelabs.homedi.homeScreenUiModule
import io.spherelabs.newtokendi.newTokenUiModel
import io.spherelabs.onboardingdi.onboardingScreenModule
import io.spherelabs.onboardingimpl.ui.OnboardingScreen
import io.spherelabs.passphrasedi.keyPasswordUiModule

@Composable
fun AnyPassNavHost() {

    ScreenRegistry {
        onboardingScreenModule()
        authScreenModule()
        homeScreenUiModule()
        addNewPasswordUiModule()
        keyPasswordUiModule()
        accountUiModule()
        generatePasswordUiModule()
        changePasswordUiModule()
        authenticatorUiModule()
        newTokenUiModel()
    }

    Navigator(OnboardingScreen())
}

