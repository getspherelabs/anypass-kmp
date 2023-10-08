package io.spherelabs.accountdomain.repository

import kotlinx.coroutines.flow.Flow

interface GetTotalPassword {
  fun execute(): Flow<Int>
}

class DefaultGetTotalPassword(
  private val repository: AccountRepository,
) : GetTotalPassword {
  override fun execute(): Flow<Int> {
    return repository.getTotalPasswords()
  }
}
