package io.spherelabs.anypass.navigation

import androidx.compose.runtime.Composable
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
import io.spherelabs.anypass.ui.account.navigation.spaceScreen
import io.spherelabs.navigation.NavHost
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.rememberNavigationController

@Composable
fun AnyPassNavHost(
    navigationController: NavigationController<Route> = rememberNavController()
) {

    NavHost(navigationController, initialState = Route.Onboarding) {
        onboardingScreen { navigationController.navigateSignIn() }
        signInScreen(
            navigateToSignUp = {
                navigationController.navigateSignUp()
            },
            navigateToConfirmPassword = {
                navigationController.navigateToPassword()
            }
        )
        signUpScreen(
            navigateToConfirmPassword = {
                navigationController.navigateToPassword()
            },
            navigateToSignIn = {
                navigationController.navigateUp()
            }
        )
        passwordScreen { navigationController.navigateToHome() }
        homeScreen(navigationController) { navigationController.navigateToAddNewPassword() }
        spaceScreen { navigationController.navigateUp() }
        addNewPasswordScreen(
            navigateToBack = {
                navigationController.navigateUp()
            },
            navigateToGeneratePassword = {
                navigationController.navigateToCreatePassword()
            }
        )
        createPasswordScreen(
            navigateToHome = {
                navigationController.navigateToHome()
            },
            navigateToBack = {
                navigationController.navigateUp()
            }
        )

    }
}


@Composable
fun rememberNavController() = rememberNavigationController<Route, Map<String, String>>(
    onSave = { route -> route.asSavable() },
    onRestore = { savable -> buildScreenFromSavable(savable) },
)
