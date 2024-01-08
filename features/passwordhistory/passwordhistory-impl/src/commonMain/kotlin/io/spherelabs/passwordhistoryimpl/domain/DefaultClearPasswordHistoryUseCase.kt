package io.spherelabs.passwordhistoryimpl.domain

import io.spherelabs.passwordhistoryapi.repository.PasswordHistoryRepository
import io.spherelabs.passwordhistoryapi.usecase.ClearAllPasswordHistoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DefaultClearPasswordHistoryUseCase(
    private val repository: PasswordHistoryRepository,
) : ClearAllPasswordHistoryUseCase {

    override suspend fun execute() {
        withContext(Dispatchers.IO) {
            repository.clearPasswordHistory()
        }
    }
}
