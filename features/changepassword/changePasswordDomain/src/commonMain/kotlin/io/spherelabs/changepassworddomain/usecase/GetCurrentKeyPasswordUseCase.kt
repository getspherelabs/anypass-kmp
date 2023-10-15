package io.spherelabs.changepassworddomain.usecase

import io.spherelabs.changepassworddomain.repository.ChangePasswordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

interface GetCurrentKeyPasswordUseCase {
    suspend fun execute(): String
}

class DefaultGetCurrentKeyPasswordUseCase(
    private val repository: ChangePasswordRepository,
) : GetCurrentKeyPasswordUseCase {
    override suspend fun execute(): String {
        return withContext(Dispatchers.IO) {
            repository.getCurrentKeyPassword()
        }
    }
}
