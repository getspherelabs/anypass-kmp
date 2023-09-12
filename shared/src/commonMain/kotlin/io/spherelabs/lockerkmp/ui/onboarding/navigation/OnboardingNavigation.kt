package io.spherelabs.lockerkmp.ui.onboarding.navigation

import io.spherelabs.lockerkmp.navigation.Route
import io.spherelabs.lockerkmp.ui.onboarding.OnboardingRoute
import io.spherelabs.lockerkmp.ui.onboarding.OnboardingScreen
import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavHostScope<Route>.onboardingScreen(
    navigateToPassword: () -> Unit
) {
    this.composable<Route.Onboarding> {
        OnboardingRoute {
            navigateToPassword.invoke()
        }
    }
}
