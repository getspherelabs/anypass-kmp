package io.spherelabs.anypass.ui.changepassword.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.anypass.ui.changepassword.ChangePasswordRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToChangePassword() {
    this.navigateTo(Route.ChangePassword)
}

fun NavHostScope<Route>.changePasswordScreen(
    navigateToBack: () -> Unit,
) {
    this.composable<Route.ChangePassword> {
        ChangePasswordRoute(navigateToBack = { navigateToBack.invoke() })
    }
}
