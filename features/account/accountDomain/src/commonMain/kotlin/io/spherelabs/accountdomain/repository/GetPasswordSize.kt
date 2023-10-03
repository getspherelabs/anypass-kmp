package io.spherelabs.accountdomain.repository

import kotlinx.coroutines.flow.Flow

interface GetPasswordSize {
  fun execute(): Flow<Int>
}

class DefaultGetPasswordSize(
  private val repository: AccountRepository,
) : GetPasswordSize {
  override fun execute(): Flow<Int> {
    return repository.getPasswordSize()
  }
}
