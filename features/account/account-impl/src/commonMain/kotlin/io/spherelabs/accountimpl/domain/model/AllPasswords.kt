package io.spherelabs.accountimpl.domain.model

data class AllPasswords(
    val sizeOfWeakPassword: Int,
    val sizeOfStrongPassword: Int,
    val totalPassword: Int,
)
