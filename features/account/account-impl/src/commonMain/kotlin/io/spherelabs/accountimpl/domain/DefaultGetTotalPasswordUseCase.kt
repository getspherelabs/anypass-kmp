package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.repository.AccountRepository
import io.spherelabs.accountapi.domain.usecase.GetTotalPasswordUseCase
import kotlinx.coroutines.flow.Flow

class DefaultGetTotalPasswordUseCase(
    private val repository: AccountRepository,
) : GetTotalPasswordUseCase {
  override fun execute(): Flow<Int> {
    return repository.getTotalPasswords()
  }
}
