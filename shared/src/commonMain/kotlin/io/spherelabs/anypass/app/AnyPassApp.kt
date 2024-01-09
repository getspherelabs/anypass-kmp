package io.spherelabs.anypass.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import io.spherelabs.accountdi.accountUiModule
import io.spherelabs.addnewpassworddi.addNewPasswordUiModule
import io.spherelabs.authdi.authScreenModule
import io.spherelabs.authenticatordi.authenticatorUiModule
import io.spherelabs.authimpl.ui.SignInScreen
import io.spherelabs.changepassworddi.changePasswordUiModule
import io.spherelabs.designsystem.hooks.useInject
import io.spherelabs.designsystem.state.collectAsStateWithLifecycle
import io.spherelabs.generatepassworddi.generatePasswordUiModule
import io.spherelabs.help.helpUiModule
import io.spherelabs.homedi.homeScreenUiModule
import io.spherelabs.newtokendi.newTokenUiModule
import io.spherelabs.onboardingdi.onboardingScreenModule
import io.spherelabs.onboardingimpl.ui.OnboardingScreen
import io.spherelabs.passphrasedi.keyPasswordUiModule
import io.spherelabs.passphraseimpl.ui.KeyPasswordScreen
import io.spherelabs.passwordhealthdi.passwordHealthUiModule
import io.spherelabs.passwordhistorydi.passwordHistoryUiModule

@Composable
fun AnyPassApp(
    onboardingSettings: SharedViewModel = useInject(),
) {
    val uiState = onboardingSettings.uiSharedState.collectAsStateWithLifecycle().value

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
        newTokenUiModule()
        passwordHealthUiModule()
        helpUiModule()
        passwordHistoryUiModule()
    }


    when (uiState) {
        SharedState.Loading -> {}
        is SharedState.Success -> {
            Navigator(
                screen =
                when {
                    uiState.isFirstTime && uiState.isCurrentUserExist -> {
                        KeyPasswordScreen()
                    }
                    uiState.isFirstTime && !uiState.isCurrentUserExist -> {
                        SignInScreen()
                    }
                    else -> {
                        OnboardingScreen()
                    }
                },
            )

        }
    }
}
