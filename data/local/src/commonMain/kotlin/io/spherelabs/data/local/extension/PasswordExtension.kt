package io.spherelabs.data.local.extension

import io.spherelabs.local.db.PasswordEntity

fun List<PasswordEntity>.countReusedPassword(): Int {
    val passwordCounts = this
        .mapNotNull { it.password }
        .groupingBy { it }
        .eachCount()

    return passwordCounts.count { it.value > 1 }
}

fun List<PasswordEntity>.reusedPasswords(): List<PasswordEntity> {
    val occurrences = this.groupingBy { it.password }.eachCount()
    val reusedPasswords = occurrences.filter { it.value > 1 }.keys
    return this.filter { it.password in reusedPasswords }.sortedBy { it.id }
}

fun List<PasswordEntity>.calculatePasswordHealth(): Double {
    val totalPasswords = this.size
    val strongPasswords = this.count { it.isStrongPassword() }
    val reusedPasswords = this.countReusedPassword()

    val healthPercentage = (strongPasswords.toDouble() / totalPasswords) * 100
    val reusedPercentage = (reusedPasswords.toDouble() / totalPasswords) * 100
    val weakPercentage = 100 - healthPercentage - reusedPercentage

    return healthPercentage - reusedPercentage - weakPercentage
}


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

