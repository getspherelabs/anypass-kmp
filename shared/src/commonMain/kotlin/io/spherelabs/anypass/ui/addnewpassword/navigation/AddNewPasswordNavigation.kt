package io.spherelabs.anypass.ui.addnewpassword.navigation

import io.spherelabs.anypass.navigation.Route

import io.spherelabs.navigation.NavHostScope
import io.spherelabs.navigation.NavigationController
import io.spherelabs.navigation.composable

fun NavigationController<Route>.navigateToAddNewPassword(password: String) {
    this.navigateUp(Route.AddNewPassword(password))
}

fun NavigationController<Route>.navigateToAddNewPassword() {
    this.navigateTo(Route.AddNewPassword())
}

fun NavHostScope<Route>.addNewPasswordScreen(
    navigateToGeneratePassword: () -> Unit,
    navigateToBack: () -> Unit,
) {
//    this.composable<Route.AddNewPassword> {
//        val password = it.state.password ?: ""
//
//        AddNewPasswordRoute(
//            currentPassword = password,
//            navigateToBack = {
//                navigateToBack.invoke()
//            },
//            navigateToGeneratePassword = {
//                navigateToGeneratePassword.invoke()
//            },
//        )
//    }
}
