package io.spherelabs.changepasswordimpl.presentation

data class ChangePasswordState(
    val currentKeyPassword: String = "",
    val getKeyPassword: String = "",
    val newKeyPassword: String = "",
    val confirmNewKeyPassword: String = "",
    val isCurrentKeyPasswordVisibility: Boolean = false,
    val isNewKeyPasswordVisibility: Boolean = false,
    val isConfirmNewKeyPasswordVisibility: Boolean = false,
    val isCurrentKeyPasswordFailed: Boolean = false,
    val isConfirmKeyPasswordFailed: Boolean = false,
    val isNewKeyPasswordFailed: Boolean = false,
    val isKeyPasswordNotSame: Boolean = false
) {
    companion object {
        val Empty = ChangePasswordState()
    }
}
