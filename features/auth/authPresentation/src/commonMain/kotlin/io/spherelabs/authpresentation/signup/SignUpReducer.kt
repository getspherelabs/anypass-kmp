package io.spherelabs.authpresentation.signup

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class SignUpReducer : Reducer<SignUpState, SignUpWish, SignUpEffect> {

    override fun reduce(state: SignUpState, wish: SignUpWish): Change<SignUpState, SignUpEffect> {
        return when (wish) {
            is SignUpWish.OnEmailChanged -> {
                expect { state.copy(email = wish.email, emailFailed = false, isLoading = false) }
            }

            SignUpWish.OnEmailFailed -> {
                expect { state.copy(emailFailed = true) }
            }

            is SignUpWish.OnNameChanged -> {
                expect { state.copy(name = wish.name, nameFailed = false, isLoading = false) }
            }

            SignUpWish.OnNameFailed -> {
                expect { state.copy(nameFailed = true, isLoading = false) }
            }

            is SignUpWish.OnPasswordChanged -> {
                expect {
                    state.copy(
                        passwordFailed = false,
                        password = wish.password,
                        isLoading = false
                    )
                }
            }

            SignUpWish.OnPasswordFailed -> {
                expect { state.copy(passwordFailed = true) }
            }

            SignUpWish.OnSignUpClick -> {
                expect { state.copy(isLoading = true) }
            }

            is SignUpWish.SignUpFailure -> {
                expect(
                    stateAction = {
                        state.copy(isLoading = false)
                    },
                    effectAction = {
                        SignUpEffect.Failure(wish.message)
                    }
                )
            }

            SignUpWish.SignUpSuccess -> {
                route {
                    SignUpEffect.AddPrivatePassword
                }
            }

            SignUpWish.TogglePasswordVisibility -> {
                expect {
                    state.copy(isPasswordVisibility = !state.isPasswordVisibility)
                }
            }

            SignUpWish.Back -> {
                route { SignUpEffect.Back }
            }

            else -> {
                unexpected { state }
            }
        }
    }
}
