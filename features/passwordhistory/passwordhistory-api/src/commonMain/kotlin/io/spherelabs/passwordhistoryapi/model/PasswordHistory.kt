package io.spherelabs.passwordhistoryapi.model

data class PasswordHistory(
    val id: String,
    val createdAt: Long,
    val password: String,
)
