package io.spherelabs.changepasswordimpl.domain.usecase

import io.spherelabs.changepasswordapi.domain.repository.ChangePasswordRepository
import io.spherelabs.changepasswordapi.domain.usecase.GetCurrentKeyPasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DefaultGetCurrentKeyPasswordUseCase(
    private val repository: ChangePasswordRepository,
) : GetCurrentKeyPasswordUseCase {
    override suspend fun execute(): String {
        return withContext(Dispatchers.IO) {
            repository.getCurrentKeyPassword()
        }
    }
}
