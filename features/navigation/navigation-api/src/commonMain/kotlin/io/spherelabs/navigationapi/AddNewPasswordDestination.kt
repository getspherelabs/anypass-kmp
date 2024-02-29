package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination


sealed interface AddNewPasswordDestination : Destination {
    object AddNewPasswordScreen : AddNewPasswordDestination
    data class UpdatePasswordScreen(val name: String, val url: String) : AddNewPasswordDestination
    data class Back(val password: String) : AddNewPasswordDestination
}
