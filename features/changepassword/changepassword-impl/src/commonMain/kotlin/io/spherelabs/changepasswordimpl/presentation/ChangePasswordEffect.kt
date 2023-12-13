package io.spherelabs.changepasswordimpl.presentation

sealed interface ChangePasswordEffect {
    data class Failure(val message: String) : ChangePasswordEffect
    data class Info(val message: String) : ChangePasswordEffect
    object Back : ChangePasswordEffect
}
