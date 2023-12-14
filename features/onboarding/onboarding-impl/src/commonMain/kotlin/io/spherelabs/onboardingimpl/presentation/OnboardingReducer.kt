package io.spherelabs.onboardingimpl.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.*
import io.spherelabs.meteor.reducer.Reducer

class OnboardingReducer : Reducer<OnboardingState, OnboardingWish, OnboardingEffect> {

    override fun reduce(
        currentState: OnboardingState,
        currentWish: OnboardingWish,
    ): Change<OnboardingState, OnboardingEffect> {
        return when (currentWish) {
            is OnboardingWish.Failed -> {
                effect { OnboardingEffect.Failure(currentWish.message) }
            }
            OnboardingWish.GetStarted -> {
                route { OnboardingEffect.SignUp }
            }
            OnboardingWish.OnCurrentUserExisted -> {
                expect {
                    currentState.copy(
                        isFirstTime = false,
                        isUserExisted = true,
                        isLoading = false,
                        isLogged = false
                    )
                }
            }
            is OnboardingWish.IsFinished -> {
                if (currentWish.value && !currentState.isUserExisted) {
                    expect(
                        effectAction = { OnboardingEffect.IsFinished },
                        stateAction = {
                            currentState.copy(
                                isFirstTime = false,
                                isLogged = currentWish.value,
                                isLoading = false,
                            )
                        },
                    )
                } else {
                    expect(
                        effectAction = { OnboardingEffect.IsFirstTime },
                        stateAction = {
                            currentState.copy(
                                isFirstTime = true,
                                isLogged = false,
                                isLoading = false,
                            )
                        },
                    )
                }
            }
            is OnboardingWish.OnLoadingChanged -> {
                expect { currentState.copy(isLoading = currentWish.loading) }
            }
            else -> unexpected { currentState }
        }
    }
}
