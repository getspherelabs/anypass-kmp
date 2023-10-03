package io.spherelabs.accountpresentation

import io.spherelabs.accountdomain.repository.GetPasswordSize
import io.spherelabs.meteor.middleware.Middleware

class AccountMiddleware(
  private val getPasswordSize: GetPasswordSize,
) : Middleware<AccountState, AccountWish> {

  override suspend fun process(
    state: AccountState,
    wish: AccountWish,
    next: suspend (AccountWish) -> Unit,
  ) {
    when (wish) {
      is AccountWish.GetSizeOfStrongPassword -> TODO()
      is AccountWish.GetSizeOfWeakPassword -> TODO()
      AccountWish.GetStartedSizeOfPassword -> {}
    }
  }
}
