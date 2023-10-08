package io.spherelabs.accountdomain.repository

import kotlinx.coroutines.flow.Flow

interface GetUser {
    fun execute(): Flow<AccountUserUi>
}

class DefaultGetUser(
    private val repository: AccountRepository,
) : GetUser {
    override fun execute(): Flow<AccountUserUi> {
        return repository.getUser()
    }

}
