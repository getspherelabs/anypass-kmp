package io.spherelabs.validation

interface PasswordValidation {
    suspend fun execute(password: String): Boolean
}

class DefaultPasswordValidation : PasswordValidation {

    override suspend fun execute(password: String): Boolean {
        return password.length in 6..30 &&
            password.any { it.isLowerCase() }
    }
}

