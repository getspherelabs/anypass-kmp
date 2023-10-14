package io.spherelabs.changepasswordpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class ChangePasswordReducer :
    Reducer<ChangePasswordState, ChangePasswordWish, ChangePasswordEffect> {

    override fun reduce(
        currentState: ChangePasswordState,
        currentWish: ChangePasswordWish,
    ): Change<ChangePasswordState, ChangePasswordEffect> {
        return when (currentWish) {
            is ChangePasswordWish.GetCurrentKeyPassword -> {
                expect {
                    currentState.copy(
                        getKeyPassword = currentWish.currentKeyPassword,
                    )
                }
            }

            is ChangePasswordWish.GetFailedCurrentKeyPassword -> {
                effect { ChangePasswordEffect.Failure(currentWish.message) }
            }

            is ChangePasswordWish.OnConfirmNewKeyPasswordChanged -> {
                expect {
                    currentState.copy(
                        confirmNewKeyPassword = currentWish.confirmKeyPassword,
                        isConfirmKeyPasswordFailed = false,
                        isKeyPasswordNotSame = false,
                    )
                }
            }

            ChangePasswordWish.OnConfirmNewKeyPasswordFailed -> {
                expect {
                    currentState.copy(
                        isConfirmKeyPasswordFailed = true,
                    )
                }
            }

            is ChangePasswordWish.OnCurrentKeyPasswordChanged -> {
                expect {
                    currentState.copy(
                        currentKeyPassword = currentWish.currentKeyPassword,
                        isKeyPasswordNotSame = false,
                        isCurrentKeyPasswordFailed = false,
                    )
                }
            }

            ChangePasswordWish.OnCurrentKeyPasswordFailed -> {
                expect {
                    currentState.copy(
                        isCurrentKeyPasswordFailed = true,
                    )
                }
            }

            is ChangePasswordWish.OnNewKeyPasswordChanged -> {
                expect {
                    currentState.copy(
                        newKeyPassword = currentWish.keyPassword,
                        isKeyPasswordNotSame = false,
                        isNewKeyPasswordFailed = false,
                    )
                }
            }

            ChangePasswordWish.OnNewKeyPasswordFailed -> {
                expect {
                    currentState.copy(
                        isNewKeyPasswordFailed = true,
                    )
                }
            }

            ChangePasswordWish.OnKeyPasswordSameFailed -> {
                expect {
                    currentState.copy(
                        isKeyPasswordNotSame = true,
                    )
                }
            }

            is ChangePasswordWish.UpdatedSuccessfully -> {
                effect { ChangePasswordEffect.Info(currentWish.message) }
            }

            ChangePasswordWish.NavigateToBack -> {
                route { ChangePasswordEffect.Back }
            }

            ChangePasswordWish.ToggleCurrentKeyPasswordVisibility -> {
                expect {
                    currentState.copy(
                        isCurrentKeyPasswordVisibility = !currentState.isCurrentKeyPasswordVisibility,
                    )
                }
            }

            ChangePasswordWish.ToggleNewKeyPasswordVisibility -> {
                expect {
                    currentState.copy(
                        isNewKeyPasswordVisibility = !currentState.isNewKeyPasswordVisibility,
                    )
                }
            }

            ChangePasswordWish.ToggleConfirmNewKeyPasswordVisibility -> {
                expect {
                    currentState.copy(
                        isConfirmNewKeyPasswordVisibility = !currentState.isConfirmNewKeyPasswordVisibility,
                    )
                }
            }

            else -> unexpected { currentState }
        }
    }
}
