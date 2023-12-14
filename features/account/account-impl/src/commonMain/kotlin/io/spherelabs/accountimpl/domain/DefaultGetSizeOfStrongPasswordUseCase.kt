package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.repository.AccountRepository
import io.spherelabs.accountapi.domain.usecase.GetSizeOfStrongPasswordUseCase
import kotlinx.coroutines.flow.Flow


class DefaultGetSizeOfStrongPasswordUseCase(
    private val repository: AccountRepository,
) : GetSizeOfStrongPasswordUseCase {
  override fun execute(): Flow<Int> {
    return repository.getSizeOfStrongPasswords()
  }
}
