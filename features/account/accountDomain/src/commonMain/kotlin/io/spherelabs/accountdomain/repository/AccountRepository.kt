package io.spherelabs.accountdomain.repository

import kotlinx.coroutines.flow.Flow

interface AccountRepository {
  fun getPasswordSize(): Flow<Int>

  fun getSizeOfStrongPasswords(): Flow<Int>

  fun getSizeOfWeakPasswords(): Flow<Int>
}
