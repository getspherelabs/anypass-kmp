package io.spherelabs.authdomain

interface EmailValidation {
    fun execute(email: String): Boolean
}

class ValidateEmail : EmailValidation {
    override fun execute(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }

    companion object {
        const val EMAIL_REGEX = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
    }
}
