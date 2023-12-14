package io.spherelabs.changepasswordimpl.domain.usecase

import io.spherelabs.changepasswordapi.domain.repository.ChangePasswordRepository
import io.spherelabs.changepasswordapi.domain.usecase.SetNewKeyPasswordUseCase

class DefaultSetNewKeyPasswordUseCase(
    private val repository: ChangePasswordRepository,
) : SetNewKeyPasswordUseCase {
    override suspend fun execute(newKeyPassword: String) {
        if (newKeyPassword.isNotEmpty()) {
            repository.setNewKeyPassword(newKeyPassword)
        }
    }
}
