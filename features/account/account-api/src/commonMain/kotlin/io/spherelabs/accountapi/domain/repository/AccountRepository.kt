package io.spherelabs.accountapi.domain.repository

import io.spherelabs.accountapi.model.AccountUser
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getSizeOfStrongPasswords(): Flow<Int>
    fun getSizeOfWeakPasswords(): Flow<Int>
    fun getTotalPasswords(): Flow<Int>
    fun getUser(): Flow<AccountUser>
}
