package io.spherelabs.newtokenimpl.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class NewTokenReducer : Reducer<NewTokenState, NewTokenWish, NewTokenEffect> {

    override fun reduce(
        currentState: NewTokenState,
        currentWish: NewTokenWish,
    ): Change<NewTokenState, NewTokenEffect> {
        return when (currentWish) {
            is NewTokenWish.Failure -> {
                effect { NewTokenEffect.Failure(currentWish.message) }
            }

            is NewTokenWish.Info -> {
                effect { NewTokenEffect.Info(currentWish.message) }
            }

            is NewTokenWish.OnDigitChanged -> {
                expect { currentState.copy(digit = currentWish.digit, digitFailed = false) }
            }

            NewTokenWish.OnDigitFailed -> {
                expect { currentState.copy(digitFailed = true) }
            }

            is NewTokenWish.OnDurationChanged -> {
                expect {
                    currentState.copy(
                        duration = currentWish.duration,
                        durationFailed = false,
                    )
                }
            }

            NewTokenWish.OnDurationFailed -> {
                expect {
                    currentState.copy(
                        durationFailed = true,
                    )
                }
            }

            is NewTokenWish.OnInfoChanged -> {
                expect {
                    currentState.copy(
                        info = currentWish.info, infoFailed = false,
                    )
                }
            }

            NewTokenWish.OnInfoFailed -> {
                expect {
                    currentState.copy(
                        infoFailed = true,
                    )
                }
            }

            NewTokenWish.OnIssuerFailed -> {
                expect {
                    currentState.copy(
                        issuerFailed = true,
                    )
                }
            }

            is NewTokenWish.OnIssuerNameChanged -> {
                expect {
                    currentState.copy(
                        issuerFailed = false, issuer = currentWish.issuer,
                    )
                }
            }

            is NewTokenWish.OnSecretChanged -> {
                expect {
                    currentState.copy(
                        secret = currentWish.secret,
                        secretFailed = false,
                    )
                }
            }

            NewTokenWish.OnServiceFailed -> {
                expect {
                    currentState.copy(
                        serviceNameFailed = true,
                    )
                }
            }

            is NewTokenWish.OnServiceNameChanged -> {
                expect {
                    currentState.copy(
                        serviceNameFailed = false, serviceName = currentWish.serviceName,
                    )
                }
            }
            is NewTokenWish.OnTypeChanged -> {
                expect {
                    currentState.copy(
                        type = currentWish.type,
                        typeFailed = false,
                    )
                }
            }
            NewTokenWish.OnTypeFailed -> {
                expect {
                    currentState.copy(
                        typeFailed = true,
                    )
                }
            }
            NewTokenWish.ToggleSecretVisibility -> {
                expect {
                    currentState.copy(
                        secretVisibility = !currentState.secretVisibility,
                    )
                }
            }
            is NewTokenWish.OnTypeExpandChanged -> {
                expect {
                    currentState.copy(
                        isTypeExpanded = currentWish.isExpanded
                    )
                }
            }
            is NewTokenWish.OnDigitExpandChanged -> {
                expect { currentState.copy(isDigitExpanded = currentWish.isExpanded) }
            }
            is NewTokenWish.OnDurationExpandChanged -> {
                expect { currentState.copy(isDurationExpanded = currentWish.isExpanded) }
            }
            else -> unexpected { currentState }
        }
    }
}
