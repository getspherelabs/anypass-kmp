package io.spherelabs.accountapi.model

data class AccountUser(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
)
