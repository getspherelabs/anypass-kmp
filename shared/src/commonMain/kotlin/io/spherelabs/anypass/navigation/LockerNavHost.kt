package io.spherelabs.anypass.navigation

import androidx.compose.runtime.Composable
import io.spherelabs.anypass.ui.addnewpassword.navigation.addNewPasswordScreen
import io.spherelabs.anypass.ui.addnewpassword.navigation.navigateToAddNewPassword
import io.spherelabs.anypass.ui.auth.navigation.navigateSignIn
import io.spherelabs.anypass.ui.auth.navigation.navigateSignUp
import io.spherelabs.anypass.ui.auth.navigation.signInScreen
import io.spherelabs.anypass.ui.auth.navigation.signUpScreen
import io.spherelabs.anypass.ui.masterpassword.navigation.navigateToPassword
import io.spherelabs.anypass.ui.masterpassword.navigation.passwordScreen
import io.spherelabs.anypass.ui.createpassword.navigation.createPasswordScreen
import io.spherelabs.anypass.ui.createpassword.navigation.navigateToCreatePassword
import io.spherelabs.anypass.ui.home.navigation.homeScreen
import io.spherelabs.anypass.ui.home.navigation.navigateToHome
import io.spherelabs.anypass.ui.onboarding.navigation.onboardingScreen
import io.spherelabs.anypass.ui.space.navigation.spaceScreen
import io.spherelabs.navigation.NavHost
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.rememberNavigationController

@Composable
fun LockerNavHost(
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
                navigationController.navigateSignIn()
            }
        )
        passwordScreen { navigationController.navigateToHome() }
        homeScreen(navigationController) { navigationController.navigateToAddNewPassword() }
        spaceScreen { navigationController.navigateUp() }
        addNewPasswordScreen { navigationController.navigateToCreatePassword() }
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