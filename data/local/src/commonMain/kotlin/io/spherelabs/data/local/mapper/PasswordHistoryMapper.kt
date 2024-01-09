package io.spherelabs.data.local.mapper

import io.spherelabs.local.db.PasswordHistoryEntity
import io.spherelabs.passwordhistoryapi.model.PasswordHistory

internal typealias PasswordHistoryRecords = List<PasswordHistoryEntity>

fun PasswordHistoryEntity.toDomain(): PasswordHistory {
    return PasswordHistory(
        id = this.id,
        createdAt = this.createdAt,
        password = requireNotNull(password) { "Password is null. Check the value of password history." },
    )
}

fun PasswordHistoryRecords.toDomain(): List<PasswordHistory> {
    return this.map { it.toDomain() }
}
