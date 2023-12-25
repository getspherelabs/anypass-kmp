package io.spherelabs.passphraseimpl.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer
import io.spherelabs.passphraseimpl.presentation.MasterPasswordState.Companion.MAX_LENGTH_OF_KEY_PASSWORD

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
                currentState.keyPasswordChanged(currentWish.password)
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

            MasterPasswordWish.ShowFingerPrint -> {
                expect(
                    stateAction = { currentState.copy(isFingerprintEnabled = currentState.isFingerprintEnabled) },
                    effectAction = { MasterPasswordEffect.ShowFingerPrint },
                )
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
}

private fun MasterPasswordState.keyPasswordChanged(newPassword: String): Change<MasterPasswordState, MasterPasswordEffect> {
    val currentPassword =
        if (this.password.length <= MAX_LENGTH_OF_KEY_PASSWORD) {
            buildString {
                append(password)
                append(newPassword)
            }
        } else {
            this.password
        }

    return Change(
        state = this.copy(password = currentPassword),
    )
}
