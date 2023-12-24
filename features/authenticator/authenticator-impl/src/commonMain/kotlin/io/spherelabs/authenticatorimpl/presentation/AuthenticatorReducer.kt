package io.spherelabs.authenticatorimpl.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer
import io.spherelabs.meteorlogger.log

class AuthenticatorReducer : Reducer<AuthenticatorState, AuthenticatorWish, AuthenticatorEffect> {

  override fun reduce(
      currentState: AuthenticatorState,
      currentWish: AuthenticatorWish,
  ): Change<AuthenticatorState, AuthenticatorEffect> {
    return when (currentWish) {
      is AuthenticatorWish.Failure -> {
        effect { AuthenticatorEffect.Failure(currentWish.message) }
      }
      is AuthenticatorWish.OnRunningChanged -> {
        expect { currentState.copy(isRunning = false) }
      }
      is AuthenticatorWish.GetAccounts -> {
        expect {
          currentState.copy(
              accounts = currentWish.accounts,
          )
        }
      }
      is AuthenticatorWish.GetOneTimePassword -> {
        log("test is ${currentWish.otp}")
        expect {
          currentState.copy(
              realTimeOtp = currentWish.otp,
          )
        }
      }
      else -> {
        unexpected { currentState }
      }
    }
  }
}
