package io.spherelabs.accountapi.domain.usecase



import io.spherelabs.accountapi.model.AccountUser
import kotlinx.coroutines.flow.Flow

interface GetUserUseCase {
    fun execute(): Flow<AccountUser>
}


