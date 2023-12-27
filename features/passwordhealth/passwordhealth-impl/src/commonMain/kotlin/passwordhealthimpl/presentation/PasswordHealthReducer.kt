package passwordhealthimpl.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class PasswordHealthReducer :
    Reducer<PasswordHealthState, PasswordHealthWish, PasswordHealthEffect> {

  override fun reduce(
      currentState: PasswordHealthState,
      currentWish: PasswordHealthWish,
  ): Change<PasswordHealthState, PasswordHealthEffect> {
    return when (currentWish) {
      is PasswordHealthWish.LoadedPasswordStats -> {
        expect {
          currentState.copy(
              stats = currentWish.stats,
              isNotAvailable = false,
          )
        }
      }
      is PasswordHealthWish.NotAvailablePasswords -> {
        expect {
          currentState.copy(
              isNotAvailable = false,
          )
        }
      }
      is PasswordHealthWish.LoadedPasswordProgress -> {
        expect {
          currentState.copy(
              currentProgress = currentWish.newProgress.toFloat(),
          )
        }
      }
      is PasswordHealthWish.LoadedPasswords -> {
        expect {
          currentState.copy(
              passwords = currentWish.passwords,
          )
        }
      }
      else -> unexpected { currentState }
    }
  }
}
