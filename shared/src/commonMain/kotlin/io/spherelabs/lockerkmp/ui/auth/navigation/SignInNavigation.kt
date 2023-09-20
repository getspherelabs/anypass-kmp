package io.spherelabs.lockerkmp.ui.auth.navigation

import io.spherelabs.lockerkmp.navigation.Route
import io.spherelabs.lockerkmp.ui.auth.SignInRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateSignIn() {
    this.navigateTo(Route.SignIn)
}

fun NavHostScope<Route>.signInScreen(
    navigateToSignUp: () -> Unit,
    navigateToConfirmPassword: () -> Unit
) {
    this.composable<Route.InputPassword> {
        SignInRoute(
            navigateToSignUp = { navigateToSignUp.invoke() },
            navigateToConfirmPassword = { navigateToConfirmPassword.invoke() }
        )
    }
}