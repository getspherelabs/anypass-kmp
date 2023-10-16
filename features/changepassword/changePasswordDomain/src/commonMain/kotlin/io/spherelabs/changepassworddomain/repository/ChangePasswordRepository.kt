package io.spherelabs.changepassworddomain.repository

interface ChangePasswordRepository {
    suspend fun getCurrentKeyPassword(): String
    suspend fun setNewKeyPassword(newKeyPassword: String)
}
