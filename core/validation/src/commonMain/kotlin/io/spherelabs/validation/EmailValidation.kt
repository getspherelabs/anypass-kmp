package io.spherelabs.validation

interface EmailValidation {
    suspend fun execute(email: String): Boolean
}

class DefaultEmailValidation : EmailValidation {

    override suspend fun execute(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }

    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
    }
}
