package io.spherelabs.passwordhistoryimpl.domain

import io.spherelabs.passwordhistoryapi.usecase.GetAllPasswordHistoryUseCase
import io.spherelabs.passwordhistoryapi.model.PasswordHistory
import io.spherelabs.passwordhistoryapi.repository.PasswordHistoryRepository
import kotlinx.coroutines.flow.Flow

class DefaultGetAllPasswordHistoryUseCase(
    private val repository: PasswordHistoryRepository,
) : GetAllPasswordHistoryUseCase {

    override fun execute(): Flow<List<PasswordHistory>> {
        return repository.getAllPasswordHistory()
    }
}
