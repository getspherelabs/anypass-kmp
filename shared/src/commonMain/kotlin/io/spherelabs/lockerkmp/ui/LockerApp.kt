package io.spherelabs.lockerkmp.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import io.spherelabs.lockerkmp.navigation.LockerNavHost

@Composable
fun LockerApp() {
    Surface {
        LockerNavHost()
    }
}