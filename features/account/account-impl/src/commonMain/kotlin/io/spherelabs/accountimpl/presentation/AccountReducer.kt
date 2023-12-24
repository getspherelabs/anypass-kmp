package io.spherelabs.accountimpl.presentation

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
        expect {
          currentState.copy(
              isFingerPrintEnabled = currentWish.isEnabled,
          )
        }
      }
      is AccountWish.GetFingerPrint -> {
        expect {
          currentState.copy(
              isFingerPrintEnabled = currentWish.isEnabled,
          )
        }
      }
      is AccountWish.GetUser -> {
        expect {
          currentState.copy(
              user = currentWish.user,
          )
        }
      }
      AccountWish.NavigateToChangePassword -> {
        route { AccountEffect.ChangePasswordRoute }
      }
      AccountWish.NavigateToBack -> {
        route { AccountEffect.Back }
      }
      else -> unexpected { currentState }
    }
  }
}
