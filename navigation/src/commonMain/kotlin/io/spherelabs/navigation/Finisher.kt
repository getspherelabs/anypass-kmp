package io.spherelabs.navigation

import androidx.compose.runtime.compositionLocalOf

/**
 * The controller for exiting from application
 */
interface Finisher {
    fun finish()
}

val LocalAppFinisher = compositionLocalOf<Finisher> {
    error("Implementation not provided")
}
