package io.spherelabs.accountdomain.usecase

import io.spherelabs.accountdomain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

interface GetTotalPasswordUseCase {
  fun execute(): Flow<Int>
}

class DefaultGetTotalPasswordUseCase(
    private val repository: AccountRepository,
) : GetTotalPasswordUseCase {
  override fun execute(): Flow<Int> {
    return repository.getTotalPasswords()
  }
}
