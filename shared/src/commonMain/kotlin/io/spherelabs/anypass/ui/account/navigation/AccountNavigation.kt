package io.spherelabs.anypass.ui.account.navigation

import io.spherelabs.anypass.navigation.Route
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToMyAccount() {
    this.navigateTo(Route.Space)
}

fun NavHostScope<Route>.accountScreen(
    navigateToHome: () -> Unit,
    navigateToChangePassword: () -> Unit,
) {
//    this.composable<Route.Space> {
//        AccountRoute(
//            navigateToBack = {
//                navigateToHome.invoke()
//            },
//            navigateToChangePassword = {
//                navigateToChangePassword.invoke()
//            },
//        )
//    }
}
