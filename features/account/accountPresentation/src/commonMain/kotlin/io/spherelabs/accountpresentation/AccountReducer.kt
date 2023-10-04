package io.spherelabs.accountpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
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

            else -> unexpected { currentState }
        }
    }
}
