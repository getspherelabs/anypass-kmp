package io.spherelabs.accountimpl.domain


import io.spherelabs.accountapi.domain.repository.AccountRepository
import io.spherelabs.accountapi.domain.usecase.GetUserUseCase
import io.spherelabs.accountapi.model.AccountUser
import kotlinx.coroutines.flow.Flow

class DefaultGetUserUseCase(
    private val repository: AccountRepository,
) : GetUserUseCase {
    override fun execute(): Flow<AccountUser> {
        return repository.getUser()
    }

}
