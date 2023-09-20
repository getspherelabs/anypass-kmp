package io.spherelabs.authpresentation.signup

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val emailFailed: Boolean = false,
    val passwordFailed: Boolean = false,
    val nameFailed: Boolean = false,
    val passwordLengthThanEight: Boolean = false,
    val isLoading: Boolean = false,
    val isPasswordVisibility: Boolean = false
) {
    companion object {
        val Empty = SignUpState()
    }
}
