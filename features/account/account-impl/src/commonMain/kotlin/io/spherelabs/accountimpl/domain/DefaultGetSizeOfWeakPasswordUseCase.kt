package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.repository.AccountRepository
import io.spherelabs.accountapi.domain.usecase.GetSizeOfWeakPasswordUseCase
import kotlinx.coroutines.flow.Flow

class DefaultGetSizeOfWeakPasswordUseCase(
    private val repository: AccountRepository,
) : GetSizeOfWeakPasswordUseCase {
  override fun execute(): Flow<Int> {
    return repository.getSizeOfWeakPasswords()
  }
}
