package io.spherelabs.authdomain

interface PasswordValidation {
    fun execute(password: String): Boolean
}

class ValidatePassword : PasswordValidation {

    override fun execute(password: String): Boolean {
        return password.length in 6..16 &&
            password.any { it.isLowerCase() } &&
            password.any { it.isUpperCase() } &&
            password.any { it.isDigit() }
    }
}
