package io.spherelabs.changepasswordnavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface ChangePasswordDestination : ScreenProvider {
    object ChangePassword : ChangePasswordDestination
}
