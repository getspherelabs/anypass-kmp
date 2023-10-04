package io.spherelabs.accountdomain.repository

import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getSizeOfStrongPasswords(): Flow<Int>

    fun getSizeOfWeakPasswords(): Flow<Int>

    fun getTotalPasswords(): Flow<Int>
}
