package io.spherelabs.features.onboardingpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class OnboardingReducer : Reducer<OnboardingState, OnboardingWish, OnboardingEffect> {

    override fun reduce(
        currentState: OnboardingState,
        currentWish: OnboardingWish
    ): Change<OnboardingState, OnboardingEffect> {
        return when (currentWish) {
            is OnboardingWish.Failed -> {
                effect { OnboardingEffect.Failure(currentWish.message) }
            }
            OnboardingWish.GetStarted -> {
                route { OnboardingEffect.SignUp }
            }
            is OnboardingWish.IsFinished -> {
                if (currentWish.value) {
                    route { OnboardingEffect.IsFinished }
                } else {
                    route { OnboardingEffect.IsFirstTime }
                }
            }
            else -> unexpected { currentState }
        }
    }
}