package io.spherelabs.anypass.navigation

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import io.spherelabs.anypass.navigation.AnyPassNavHost

@Composable
fun AnyPassApp() {
    Surface {
        AnyPassNavHost()
    }
}
