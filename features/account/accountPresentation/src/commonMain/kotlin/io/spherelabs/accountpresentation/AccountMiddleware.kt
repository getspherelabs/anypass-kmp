package io.spherelabs.accountpresentation

import io.spherelabs.accountdomain.repository.GetSizeOfStrongPassword
import io.spherelabs.accountdomain.repository.GetSizeOfWeakPassword
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class AccountMiddleware(
    private val getWeakPasswordSize: GetSizeOfWeakPassword,
    private val getStrongPasswordSize: GetSizeOfStrongPassword,
) : Middleware<AccountState, AccountWish> {

    override suspend fun process(
        state: AccountState,
        wish: AccountWish,
        next: suspend (AccountWish) -> Unit,
    ) {
        when (wish) {
            AccountWish.GetStartedSizeOfStrongPassword -> {
                handleStrongPassword(next)
            }

            AccountWish.GetStartedSizeOfWeakPassword -> {
                handleWeakPassword(next)
            }

            else -> {}
        }
    }

    private suspend inline fun handleStrongPassword(noinline next: suspend (AccountWish) -> Unit) {
        getStrongPasswordSize.execute().collectLatest { result ->
            next.invoke(AccountWish.GetSizeOfStrongPassword(result))
        }
    }

    private suspend inline fun handleWeakPassword(noinline next: suspend (AccountWish) -> Unit) {
        getWeakPasswordSize.execute().collectLatest { result ->
            next.invoke(AccountWish.GetSizeOfWeakPassword(result))
        }
    }
}
