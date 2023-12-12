package io.spherelabs.anypass.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import io.spherelabs.accountdi.accountUiModule
import io.spherelabs.addnewpassworddi.addNewPasswordUiModule
import io.spherelabs.authdi.authScreenModule
import io.spherelabs.generatepassworddi.generatePasswordUiModule
import io.spherelabs.homedi.homeScreenUiModule
import io.spherelabs.navigation.rememberNavigationController
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
    }
    Navigator(OnboardingScreen())
//    NavHost(navigationController, initialState = Route.Onboarding) {
//        onboardingScreen { navigationController.navigateSignIn() }
//        signInScreen(
//            navigateToSignUp = {
//                navigationController.navigateSignUp()
//            },
//            navigateToConfirmPassword = {
//                navigationController.navigateToPassword()
//            },
//        )
//        signUpScreen(
//            navigateToConfirmPassword = {
//                navigationController.navigateToPassword()
//            },
//            navigateToSignIn = {
//                navigationController.navigateUp()
//            },
//        )
//        passwordScreen { navigationController.navigateToHome() }
//        homeScreen(
//            navigateToCreatePassword = {
//                navigationController.navigateToAddNewPassword()
//            },
//            navigateToMyAccount = {
//                navigationController.navigateToMyAccount()
//            },
//            navigateToAuthenticator = {
//                navigationController.navigateToAuthenticator()
//            },
//        )
//        accountScreen(
//            navigateToChangePassword = {
//                navigationController.navigateToChangePassword()
//            },
//            navigateToHome = {
//                navigationController.navigateUp()
//            },
//        )
//        addNewPasswordScreen(
//            navigateToBack = {
//                navigationController.navigateUp()
//            },
//            navigateToGeneratePassword = {
//                navigationController.navigateToCreatePassword()
//            },
//        )
//        createPasswordScreen(
//            navigateToHome = {
//                navigationController.navigateToHome()
//            },
//            navigateToBack = {
//                navigationController.navigateUp()
//            },
//            navigateToUse = {
//                navigationController.navigateToAddNewPassword(it)
//            },
//        )
//        changePasswordScreen {
//            navigationController.navigateUp()
//        }
//
//        newTokenScreen { }
//        authenticatorScreen(
//            navigateToNewToken = {
//                navigationController.navigateToNewToken()
//            },
//            navigateToBack = {},
//        )
//        newTokenScreen {
//
//        }
//    }
}


@Composable
fun rememberNavController() = rememberNavigationController<Route, Map<String, String>>(
    onSave = { route -> route.asSavable() },
    onRestore = { savable -> buildScreenFromSavable(savable) },
)
