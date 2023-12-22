package io.spherelabs.navigationapi

import io.spherelabs.navigationapi.core.Destination


sealed interface AddNewPasswordDestination : Destination {
    object AddNewPasswordScreen : Destination
    data class Back(val password: String) : Destination
}
