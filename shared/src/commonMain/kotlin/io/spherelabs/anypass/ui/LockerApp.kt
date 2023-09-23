package io.spherelabs.anypass.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import io.spherelabs.anypass.navigation.LockerNavHost

@Composable
fun LockerApp() {
    Surface {
        LockerNavHost()
    }
}