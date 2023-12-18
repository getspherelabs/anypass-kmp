package io.spherelabs.data.local.db

import io.spherelabs.local.db.PasswordEntity

fun List<PasswordEntity>.countReusedPassword(): Int {
    val passwordCounts = this
        .mapNotNull { it.password } // Filter out null passwords
        .groupingBy { it }
        .eachCount()

    return passwordCounts.count { it.value > 1 }
}
