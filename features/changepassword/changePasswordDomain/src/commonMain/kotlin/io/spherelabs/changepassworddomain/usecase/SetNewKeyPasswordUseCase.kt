package io.spherelabs.changepassworddomain.usecase

import io.spherelabs.changepassworddomain.repository.ChangePasswordRepository

interface SetNewKeyPasswordUseCase {
    suspend fun execute(newKeyPassword: String)
}

class DefaultSetNewKeyPasswordUseCase(
    private val repository: ChangePasswordRepository,
) : SetNewKeyPasswordUseCase {
    override suspend fun execute(newKeyPassword: String) {
        if (newKeyPassword.isNotEmpty()) {
            repository.setNewKeyPassword(newKeyPassword)
        }
    }
}
