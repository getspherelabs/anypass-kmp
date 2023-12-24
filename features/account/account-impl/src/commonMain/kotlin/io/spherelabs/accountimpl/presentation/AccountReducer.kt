package io.spherelabs.accountimpl.presentation

import io.spherelabs.accountapi.model.AccountUser
import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class AccountReducer : Reducer<AccountState, AccountWish, AccountEffect> {

    override fun reduce(
        currentState: AccountState,
        currentWish: AccountWish,
    ): Change<AccountState, AccountEffect> {
        return when (currentWish) {
            is AccountWish.GetSizeOfStrongPassword -> {
                expect {
                    currentState.copy(
                        sizeOfStrongPassword = currentWish.size,
                    )
                }
            }

            is AccountWish.GetSizeOfWeakPassword -> {
                expect {
                    currentState.copy(
                        sizeOfWeakPassword = currentWish.size,
                    )
                }
            }

            is AccountWish.GetTotalPassword -> {
                expect {
                    currentState.copy(
                        sizeOfTotalPassword = currentWish.size,
                    )
                }
            }

            is AccountWish.OnFingerPrintChanged -> {
                currentState.fingerPrintChanged(currentWish.isEnabled)
            }

            is AccountWish.GetFingerPrint -> {
                currentState.fingerPrintChanged(currentWish.isEnabled)
            }

            is AccountWish.GetUser -> {
                currentState.userChanged(currentWish.user)
            }

            AccountWish.NavigateToChangePassword -> {
                route { AccountEffect.ChangePasswordRoute }
            }

            AccountWish.NavigateToBack -> {
                route { AccountEffect.Back }
            }

            is AccountWish.LogoutChanged -> {
                currentState.logoutChanged(currentWish.isLogout)
            }

            else -> unexpected { currentState }
        }
    }
}

fun AccountState.logoutChanged(isLogout: Boolean): Change<AccountState, AccountEffect> {
    return if (isLogout) Change(effect = AccountEffect.SignInScreen) else Change(state = this)
}

fun AccountState.userChanged(user: AccountUser): Change<AccountState, AccountEffect> {
    return Change(
        state = this.copy(user = user),
    )
}

fun AccountState.fingerPrintChanged(isFingerPrintEnabled: Boolean): Change<AccountState, AccountEffect> {
    return Change(
        state = this.copy(isFingerPrintEnabled = isFingerPrintEnabled),
    )
}
