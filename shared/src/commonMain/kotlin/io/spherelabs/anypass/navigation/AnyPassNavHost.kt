package io.spherelabs.anypass.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.navigator.Navigator
import io.spherelabs.anypass.ui.account.navigation.navigateToMyAccount
import io.spherelabs.anypass.ui.addnewpassword.navigation.addNewPasswordScreen
import io.spherelabs.anypass.ui.addnewpassword.navigation.navigateToAddNewPassword
import io.spherelabs.anypass.ui.auth.navigation.navigateSignIn
import io.spherelabs.anypass.ui.auth.navigation.navigateSignUp
import io.spherelabs.anypass.ui.auth.navigation.signInScreen
import io.spherelabs.anypass.ui.auth.navigation.signUpScreen
import io.spherelabs.anypass.ui.keypassword.navigation.navigateToPassword
import io.spherelabs.anypass.ui.keypassword.navigation.passwordScreen
import io.spherelabs.anypass.ui.generatepassword.navigation.createPasswordScreen
import io.spherelabs.anypass.ui.generatepassword.navigation.navigateToCreatePassword
import io.spherelabs.anypass.ui.home.navigation.homeScreen
import io.spherelabs.anypass.ui.home.navigation.navigateToHome
import io.spherelabs.anypass.ui.onboarding.navigation.onboardingScreen
import io.spherelabs.anypass.ui.account.navigation.accountScreen
import io.spherelabs.anypass.ui.authenticator.navigation.authenticatorScreen
import io.spherelabs.anypass.ui.authenticator.navigation.navigateToAuthenticator
import io.spherelabs.anypass.ui.changepassword.navigation.changePasswordScreen
import io.spherelabs.anypass.ui.changepassword.navigation.navigateToChangePassword
import io.spherelabs.anypass.ui.newtoken.navigation.navigateToNewToken
import io.spherelabs.anypass.ui.newtoken.navigation.newTokenScreen
import io.spherelabs.authdi.authScreenModule
import io.spherelabs.authimpl.ui.SignInScreen
import io.spherelabs.navigation.NavHost
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.rememberNavigationController

@Composable
fun AnyPassNavHost() {

    ScreenRegistry {
        authScreenModule()
    }
            Navigator(SignInScreen())
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
