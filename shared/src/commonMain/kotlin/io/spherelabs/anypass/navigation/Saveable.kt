package io.spherelabs.anypass.navigation

import io.spherelabs.anypass.alias.Routes
import io.spherelabs.anypass.alias.Savable

private const val KEY_ROUTE_NAME = "route_name"
private const val KEY_PASSWORD = "password"

fun <R : Route> R.asSavable(): Map<String, String> {
    return when (this) {
        is Route.Onboarding -> savable<Route.Onboarding>()
        is Route.MasterPassword -> savable<Route.MasterPassword>()
        is Route.Home -> savable<Route.Home>()
        is Route.CreatePassword -> savable<Route.CreatePassword>()
        is Route.AddNewPassword -> {
            password?.let {
                savable<Route.AddNewPassword>(KEY_PASSWORD to it)
            } ?: savable<Route.AddNewPassword>()
        }

        is Route.Space -> savable<Route.Space>()
        is Route.SignUp -> savable<Route.SignUp>()
        is Route.SignIn -> savable<Route.SignIn>()
        is Route.Authenticator -> savable<Route.Authenticator>()
        is Route.NewToken -> savable<Route.NewToken>()
        else -> error("Can't save state for screen: ${this@asSavable}. Reason: Undefined")
    }
}

/**
 * Constructs [Route] from [savable] map data
 */
fun buildScreenFromSavable(savable: Savable): Route {
    return when (val routeName = savable[KEY_ROUTE_NAME]) {
        routeName<Route.Onboarding>() -> Route.Onboarding
        routeName<Route.MasterPassword>() -> Route.MasterPassword
        routeName<Route.CreatePassword>() -> Route.CreatePassword
        routeName<Route.Home>() -> Route.Home
        routeName<Route.AddNewPassword>() -> {
            val passwordArgument = savable[KEY_PASSWORD] ?: ""
            Route.AddNewPassword(passwordArgument)
        }
        routeName<Route.SignUp>() -> Route.SignUp
        routeName<Route.SignIn>() -> Route.SignIn
        routeName<Route.Space>() -> Route.Space
        routeName<Route.Authenticator>() -> Route.Authenticator
        routeName<Route.NewToken>() -> Route.NewToken
        else -> error("Can't restore state for screen: $routeName. Reason: Undefined")
    }
}

private inline fun <reified R : Route> savable(vararg pairs: Routes): Savable {
    return try {
        mapOf(KEY_ROUTE_NAME to routeName<R>()) + pairs.toMap()
    } catch (e: Exception) {
        error("${e.message}")
    }
}

inline fun <reified ROUTE : Route> routeName(): String = ROUTE::class.qualifiedName!!
