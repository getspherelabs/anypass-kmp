package io.spherelabs.lockerkmp.navigation

import androidx.compose.runtime.Composable
import io.spherelabs.lockerkmp.ui.addnewpassword.navigation.addNewPasswordScreen
import io.spherelabs.lockerkmp.ui.addnewpassword.navigation.navigateToAddNewPassword
import io.spherelabs.lockerkmp.ui.confirmpassword.navigation.navigateToPassword
import io.spherelabs.lockerkmp.ui.confirmpassword.navigation.passwordScreen
import io.spherelabs.lockerkmp.ui.createpassword.navigation.createPasswordScreen
import io.spherelabs.lockerkmp.ui.createpassword.navigation.navigateToCreatePassword
import io.spherelabs.lockerkmp.ui.home.navigation.homeScreen
import io.spherelabs.lockerkmp.ui.home.navigation.navigateToHome
import io.spherelabs.lockerkmp.ui.onboarding.navigation.onboardingScreen
import io.spherelabs.navigation.NavHost
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.rememberNavigationController

@Composable
fun LockerNavHost(
    navigationController: NavigationController<Route> = rememberNavController()
) {

    NavHost(navigationController, initialState = Route.Onboarding) {
        onboardingScreen {
            navigationController.navigateToPassword()
        }
        passwordScreen {
            navigationController.navigateToHome()
        }
        homeScreen {
            navigationController.navigateToAddNewPassword()
        }
        addNewPasswordScreen(
            navigateToGeneratePassword = {
                navigationController.navigateToCreatePassword()
            }
        )
        createPasswordScreen(
            navigateToHome = {}
        ) {
            navigationController.navigateUp()
        }
    }
}


@Composable
fun rememberNavController() = rememberNavigationController<Route, Map<String, String>>(
    onSave = { route -> route.asSavable() },
    onRestore = { savable -> buildScreenFromSavable(savable) },
)