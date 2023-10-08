package io.spherelabs.accountdomain.repository

import kotlinx.coroutines.flow.Flow

interface GetSizeOfStrongPassword {
  fun execute(): Flow<Int>
}

class DefaultGetSizeOfStrongPassword(
  private val repository: AccountRepository,
) : GetSizeOfStrongPassword {
  override fun execute(): Flow<Int> {
    return repository.getSizeOfStrongPasswords()
  }
}
