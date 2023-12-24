package io.spherelabs.authimpl.presentation.signin

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class SignInReducer : Reducer<SignInState, SignInWish, SignInEffect> {

  override fun reduce(state: SignInState, wish: SignInWish): Change<SignInState, SignInEffect> {
    return when (wish) {
      is SignInWish.OnEmailChanged -> {
        expect { state.copy(email = wish.email.trim(), emailFailed = false, isLoading = false) }
      }
      SignInWish.OnEmailFailed -> expect { state.copy(emailFailed = true, isLoading = false) }
      is SignInWish.OnPasswordChanged -> {
        expect {
          state.copy(
              passwordFailed = false,
              password = wish.password.trim(),
              isLoading = false,
          )
        }
      }
      SignInWish.OnPasswordFailed -> {
        expect {
          state.copy(
              passwordFailed = true,
              isLoading = false,
          )
        }
      }
      SignInWish.OnLoginClicked -> expect { state.copy(isLoading = true) }
      is SignInWish.SignInFailure -> {
        expect(
            stateAction = { state.copy(isLoading = false) },
            effectAction = { SignInEffect.Failure(wish.message) },
        )
      }
      SignInWish.SignInSuccess -> {
        expect(
            stateAction = { state.copy(isLoading = false) },
            effectAction = { SignInEffect.KeyPassword },
        )
      }
      SignInWish.TogglePasswordVisibility -> {
        expect { state.copy(isPasswordVisibility = !state.isPasswordVisibility) }
      }
      SignInWish.CreateNewClicked -> {
        route { SignInEffect.CreateNew }
      }
      is SignInWish.HasCurrentUser -> {
        expect { state.copy(isCurrentUserExist = wish.value) }
      }
      else -> {
        unexpected { state }
      }
    }
  }
}
