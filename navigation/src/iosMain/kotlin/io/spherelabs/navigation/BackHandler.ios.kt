package io.spherelabs.navigation

import androidx.compose.runtime.Composable

/**
 *
 * This [BackHandler] is intended for use in Composable functions on iOS platform to handle the back
 * action, although on iOS, there is generally no need to explicitly handle the system's back button.
 *
 * @param enabled Indicates whether the back handling is enabled or disabled.
 * @param onBack A lambda function to be executed when the back action is triggered.
 */
@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    // There's no need to handle the system's back button on iOS platform
}