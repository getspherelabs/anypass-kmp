package io.spherelabs.addnewpasswordnavigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface AddNewPasswordSharedScreen : ScreenProvider {
    object AddNewPasswordScreen : ScreenProvider
    data class Back(val password: String) : ScreenProvider
}
