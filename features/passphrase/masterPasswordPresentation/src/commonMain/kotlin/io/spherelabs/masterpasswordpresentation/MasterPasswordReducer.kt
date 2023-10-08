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
            is MasterPasswordWish.OnConfirmPasswordChanged -> {
                val newPassword: String = buildString {
                    if (currentState.password.length == 4) {
                        println("Wish password: ${currentState.password} in confirm password changed")
                        val confirmPassword: String = currentState.confirmPassword ?: ""
                        if (confirmPassword.length < 4) {
                            append(confirmPassword + currentWish.confirmPassword)
                        }
                    }
                }

                println("Wish new password: $newPassword")

                expect {
                    currentState.copy(
                        password = currentState.password,
                        confirmPassword = newPassword,
                    )
                }
            }
            is MasterPasswordWish.OnMasterPasswordChanged -> {
                val newPassword: String = buildString {
                    if (currentState.password.length < 4) {
                        append(currentState.password + currentWish.password)
                    } else {
                        append(currentState.password)
                    }
                }

                expect {
                    currentState.copy(
                        password = newPassword,
                        isInitialPasswordExisted = currentState.password.length == 4,
                    )
                }
            }
            is MasterPasswordWish.SetPasswordFailure -> {
                effect { MasterPasswordEffect.Failure(currentWish.message) }
            }
            MasterPasswordWish.SetPasswordSuccessFully -> {
                route { MasterPasswordEffect.Home }
            }
            MasterPasswordWish.ClearPassword -> {
                expect {
                    currentState.copy(
                        password = "",
                        confirmPassword = null,
                        isInitialPasswordExisted = false,
                    )
                }
            }
            is MasterPasswordWish.OnPasswordCellChanged -> {
                expect { currentState.copy(password = currentWish.password) }
            }
            is MasterPasswordWish.OnConfirmPasswordCellChanged -> {
                expect { currentState.copy(confirmPassword = currentWish.confirmPassword) }
            }
            is MasterPasswordWish.GetFingerprint -> {
                expect { currentState.copy(isFingerprintEnabled = currentWish.isEnabled) }
            }
            MasterPasswordWish.NavigateToHome -> {
                route { MasterPasswordEffect.Home }
            }
            else -> {
                unexpected { currentState }
            }
        }
    }
}
