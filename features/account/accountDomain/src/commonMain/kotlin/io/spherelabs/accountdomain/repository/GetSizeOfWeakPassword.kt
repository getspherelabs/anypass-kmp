package io.spherelabs.accountdomain.repository

import kotlinx.coroutines.flow.Flow

interface GetSizeOfWeakPassword {
    fun execute(): Flow<Int>
}

class DefaultGetSizeOfWeakPassword(
    private val repository: AccountRepository,
) : GetSizeOfWeakPassword {
    override fun execute(): Flow<Int> {
        return repository.getSizeOfWeakPasswords()
    }
}
