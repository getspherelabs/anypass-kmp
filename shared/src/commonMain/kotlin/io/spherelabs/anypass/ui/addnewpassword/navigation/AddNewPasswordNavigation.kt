package io.spherelabs.anypass.ui.addnewpassword.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.addnewpassword.AddNewPasswordRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToAddNewPassword() {
    this.navigateTo(Route.AddNewPassword)
}

fun NavHostScope<Route>.addNewPasswordScreen(
    navigateToGeneratePassword: () -> Unit,
    navigateToBack: () -> Unit,
) {
    this.composable<Route.AddNewPassword> {
        AddNewPasswordRoute(
            navigateToBack = {
                navigateToBack.invoke()
            },
            navigateToGeneratePassword = {
                navigateToGeneratePassword.invoke()
            },
        )
    }
}
