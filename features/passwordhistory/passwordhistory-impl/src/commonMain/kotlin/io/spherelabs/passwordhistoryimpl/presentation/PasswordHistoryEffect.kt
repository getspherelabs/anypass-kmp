package io.spherelabs.passwordhistoryimpl.presentation

sealed interface PasswordHistoryEffect {
    data class Failure(val message: String) : PasswordHistoryEffect
}
