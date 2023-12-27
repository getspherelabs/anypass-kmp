package io.spherelabs.authimpl.presentation.signin

data class SignInState(
    val email: String = "",
    val password: String = "",
    val emailFailed: Boolean = false,
    val passwordFailed: Boolean = false,
    val nameFailed: Boolean = false,
    val isLoading: Boolean = false,
    val isPasswordVisibility: Boolean = false,
    val isCurrentUserExist: Boolean = false
) {
  companion object {
    val Empty = SignInState()
  }
}
