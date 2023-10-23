package io.spherelabs.accountdomain.usecase

import io.spherelabs.accountdomain.repository.AccountRepository
import io.spherelabs.accountdomain.repository.AccountUserUi
import kotlinx.coroutines.flow.Flow

interface GetUserUseCase {
    fun execute(): Flow<AccountUserUi>
}

class DefaultGetUserUseCase(
    private val repository: AccountRepository,
) : GetUserUseCase {
    override fun execute(): Flow<AccountUserUi> {
        return repository.getUser()
    }

}
