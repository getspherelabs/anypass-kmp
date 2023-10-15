package io.spherelabs.masterpasswordpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class MasterPasswordReducer :
    Reducer<MasterPasswordState, MasterPasswordWish, MasterPasswordEffect> {

    override fun reduce(
        currentState: MasterPasswordState,
        currentWish: MasterPasswordWish,
    ): Change<MasterPasswordState, MasterPasswordEffect> {

        return when (currentWish) {
            MasterPasswordWish.PasswordExisted -> {
                expect { currentState.copy(isExistPassword = true) }
            }

            is MasterPasswordWish.OnMasterPasswordChanged -> {
                val newPassword = if (currentState.password.length <= MAX_PASSWORD_LENGTH) {
                    buildString {
                        append(currentState.password)
                        append(currentWish.password)
                    }
                } else {
                    currentState.password
                }

                expect {
                    currentState.copy(
                        password = newPassword,
                    )
                }
            }

            is MasterPasswordWish.SetPasswordFailure -> {
                effect { MasterPasswordEffect.Failure(currentWish.message) }
            }


            MasterPasswordWish.ClearPassword -> {
                expect {
                    currentState.copy(
                        password = "",
                    )
                }
            }

            is MasterPasswordWish.OnPasswordCellChanged -> {
                expect { currentState.copy(password = currentWish.password) }
            }

            is MasterPasswordWish.GetFingerprint -> {
                expect { currentState.copy(isFingerprintEnabled = currentWish.isEnabled) }
            }

            MasterPasswordWish.NavigateToHome -> {
                route { MasterPasswordEffect.Home }
            }

            is MasterPasswordWish.IsNotMatched -> {
                effect { MasterPasswordEffect.Failure(currentWish.message) }
            }

            else -> {
                unexpected { currentState }
            }
        }
    }

    companion object {
        private const val MAX_PASSWORD_LENGTH = 3
    }
}
