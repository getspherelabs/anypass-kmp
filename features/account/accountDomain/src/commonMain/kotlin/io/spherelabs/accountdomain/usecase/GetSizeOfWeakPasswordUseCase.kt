package io.spherelabs.accountdomain.usecase

import io.spherelabs.accountdomain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

interface GetSizeOfWeakPasswordUseCase {
  fun execute(): Flow<Int>
}

class DefaultGetSizeOfWeakPasswordUseCase(
    private val repository: AccountRepository,
) : GetSizeOfWeakPasswordUseCase {
  override fun execute(): Flow<Int> {
    return repository.getSizeOfWeakPasswords()
  }
}
