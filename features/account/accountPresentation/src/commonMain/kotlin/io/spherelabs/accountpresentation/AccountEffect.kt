package io.spherelabs.accountpresentation

sealed interface AccountEffect {
    data class Failure(val message: String) : AccountEffect
    object ChangePasswordRoute : AccountEffect
    object Back : AccountEffect
}
