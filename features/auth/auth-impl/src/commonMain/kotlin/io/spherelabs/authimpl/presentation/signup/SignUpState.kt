package io.spherelabs.authimpl.presentation.signup

data class SignUpState(
  val email: String = "",
  val password: String = "",
  val name: String = "",
  val emailFailed: Boolean = false,
  val passwordFailed: Boolean = false,
  val nameFailed: Boolean = false,
  val keyPasswordFailed: Boolean = false,
  val keyPassword: String = "",
  val confirmKeyPassword: String = "",
  val passwordLengthThanEight: Boolean = false,
  val isLoading: Boolean = false,
  val isPasswordVisibility: Boolean = false,
  val isKeyPasswordVisibility: Boolean = false,
  val isConfirmKeyPasswordVisibility: Boolean = false,
  val isKeyPasswordSame: Boolean = false,
) {
  companion object {
    val Empty = SignUpState()
  }
}
