package io.spherelabs.lockerkmp.navigation

private const val KEY_ROUTE_NAME = "route_name"

fun <R : Route> R.asSavable(): Map<String, String> {
    return when (this) {
        is Route.Onboarding -> savable<Route.Onboarding>()
        is Route.InputPassword -> savable<Route.InputPassword>()
        is Route.Home -> savable<Route.Home>()
        is Route.CreatePassword -> savable<Route.CreatePassword>()
        is Route.AddNewPassword -> savable<Route.AddNewPassword>()
        is Route.Space -> savable<Route.Space>()
        is Route.SignUp -> savable<Route.SignUp>()
        is Route.SignIn -> savable<Route.SignIn>()
        else -> error("Can't save state for screen: ${this@asSavable}. Reason: Undefined")
    }
}

/**
 * Constructs [Route] from [savable] map data
 */
fun buildScreenFromSavable(savable: Map<String, String>): Route {
    return when (val routeName = savable[KEY_ROUTE_NAME]) {
        routeName<Route.Onboarding>() -> Route.Onboarding
        routeName<Route.InputPassword>() -> Route.InputPassword
        routeName<Route.CreatePassword>() -> Route.CreatePassword
        routeName<Route.Home>() -> Route.Home
        routeName<Route.AddNewPassword>() -> Route.AddNewPassword
        routeName<Route.SignUp>() -> Route.SignUp
        routeName<Route.SignIn>() -> Route.SignIn
        routeName<Route.Space>() -> Route.Space
        else -> error("Can't restore state for screen: $routeName. Reason: Undefined")
    }
}

/**
 * Creates savable Map for screen [S] having entry of [pairs] in the map
 */
private inline fun <reified R : Route> savable(
    vararg pairs: Pair<String, String>,
) = buildMap<String, String> {
    try {
        put(KEY_ROUTE_NAME, routeName<R>())
        putAll(pairs)
    } catch (e: Exception) {
        error("${e.message}")
    }

}

inline fun <reified ROUTE : Route> routeName(): String = ROUTE::class.qualifiedName!!