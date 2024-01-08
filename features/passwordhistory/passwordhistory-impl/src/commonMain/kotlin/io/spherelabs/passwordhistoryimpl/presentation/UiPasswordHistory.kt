package io.spherelabs.passwordhistoryimpl.presentation

import io.spherelabs.passwordhistoryapi.model.PasswordHistory

data class UiPasswordHistory(
    val id: String,
    val createdAt: Long,
    val password: String,
)

fun PasswordHistory.toUi(): UiPasswordHistory {
    return UiPasswordHistory(id, createdAt, password)
}

fun UiPasswordHistory.toDomain(): PasswordHistory {
    return PasswordHistory(id, createdAt, password)
}
