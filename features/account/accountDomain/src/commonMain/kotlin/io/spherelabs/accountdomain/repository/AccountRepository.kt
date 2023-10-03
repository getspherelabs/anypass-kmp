package io.spherelabs.accountdomain.repository

import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getPasswordSize(): Flow<Int>
}
