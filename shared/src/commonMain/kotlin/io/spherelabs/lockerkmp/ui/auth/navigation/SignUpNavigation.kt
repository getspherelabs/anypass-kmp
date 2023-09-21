package io.spherelabs.lockerkmp.ui.auth.navigation

import io.spherelabs.lockerkmp.navigation.Route
import io.spherelabs.lockerkmp.ui.auth.SignUpRoute
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateSignUp() {
    this.navigateTo(Route.SignUp)
}

fun NavHostScope<Route>.signUpScreen(
    navigateToSignIn: () -> Unit,
    navigateToConfirmPassword: () -> Unit
) {
    this.composable<Route.SignUp> {
        SignUpRoute(
            navigateToSignIn = { navigateToSignIn.invoke() },
            navigateToAddPrivatePassword = { navigateToConfirmPassword.invoke() }
        )
    }
}