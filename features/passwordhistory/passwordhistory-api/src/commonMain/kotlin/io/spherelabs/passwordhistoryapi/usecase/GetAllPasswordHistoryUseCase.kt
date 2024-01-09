package io.spherelabs.passwordhistoryapi.usecase

import io.spherelabs.passwordhistoryapi.model.PasswordHistory
import kotlinx.coroutines.flow.Flow

interface GetAllPasswordHistoryUseCase {
    fun execute(): Flow<List<PasswordHistory>>
}
