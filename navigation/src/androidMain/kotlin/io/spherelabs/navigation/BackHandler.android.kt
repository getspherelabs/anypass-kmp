package io.spherelabs.navigation

import androidx.compose.runtime.Composable

/**
 * An effect for handling presses of the system back button.
 *
 * If this is called by nested composables, if enabled, the inner most composable will consume
 * the call to system back and invoke its lambda. The call will continue to propagate up until it
 * finds an enabled BackHandler.
 *
 * @param enabled if this BackHandler should be enabled
 * @param onBack the action invoked by pressing the system back
 */
@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    androidx.activity.compose.BackHandler(enabled, onBack)
}