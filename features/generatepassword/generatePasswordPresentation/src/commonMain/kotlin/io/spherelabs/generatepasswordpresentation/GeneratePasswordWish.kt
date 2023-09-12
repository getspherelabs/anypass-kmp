package io.spherelabs.generatepasswordpresentation


sealed interface GeneratePasswordWish {
    data class OnPasswordChanged(val password: String) : GeneratePasswordWish
    data class GeneratePassword(
        val uppercaseLength: Int = 0,
        val digitLength: Int = 0,
        val lowercaseLength: Int = 0,
        val specialLength: Int = 0,
        val length: Int = 10
    ) : GeneratePasswordWish

    data class GeneratePasswordFailed(val message: String) : GeneratePasswordWish

    data class OnUppercaseLengthChanged(val value: Float) : GeneratePasswordWish
    data class OnDigitLengthChanged(val value: Float) : GeneratePasswordWish
}