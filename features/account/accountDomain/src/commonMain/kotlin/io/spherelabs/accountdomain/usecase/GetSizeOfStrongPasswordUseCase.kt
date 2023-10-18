package io.spherelabs.accountdomain.usecase

import io.spherelabs.accountdomain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

interface GetSizeOfStrongPasswordUseCase {
  fun execute(): Flow<Int>
}

class DefaultGetSizeOfStrongPasswordUseCase(
    private val repository: AccountRepository,
) : GetSizeOfStrongPasswordUseCase {
  override fun execute(): Flow<Int> {
    return repository.getSizeOfStrongPasswords()
  }
}
