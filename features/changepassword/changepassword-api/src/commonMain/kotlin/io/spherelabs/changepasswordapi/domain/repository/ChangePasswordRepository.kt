package io.spherelabs.changepasswordapi.domain.repository

interface ChangePasswordRepository {
    suspend fun getCurrentKeyPassword(): String
    suspend fun setNewKeyPassword(newKeyPassword: String)
}
