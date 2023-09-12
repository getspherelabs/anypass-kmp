package io.spherelabs.lockerkmp.ui.addnewpassword.navigation

import io.spherelabs.lockerkmp.navigation.Route
import io.spherelabs.lockerkmp.ui.addnewpassword.AddNewPasswordScreen
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToAddNewPassword() {
    this.navigateTo(Route.AddNewPassword)
}

fun NavHostScope<Route>.addNewPasswordScreen(
    navigateToHome: () -> Unit
) {
    this.composable<Route.AddNewPassword> {
        AddNewPasswordScreen()
    }
}