package io.spherelabs.generatepasswordapi.domain.repository

interface GeneratePasswordRepository {
    suspend fun insertPasswordHistory(password: String, createdAt: Long)
}
