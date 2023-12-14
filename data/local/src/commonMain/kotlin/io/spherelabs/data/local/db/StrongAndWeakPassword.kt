package io.spherelabs.data.local.db
import io.spherelabs.local.db.PasswordEntity

fun PasswordEntity.isStrongPassword(): Boolean {
    return this.password?.let {
        val minLength = 8
        val uppercase = this.password.any { it.isUpperCase() }
        val lowercase = this.password.any { it.isLowerCase() }
        val digit = this.password.any { it.isDigit() }
        val special = this.password.any { it.isLetterOrDigit().not() }

        return password.length >= minLength &&
            uppercase && lowercase && digit && special
    } ?: false
}
