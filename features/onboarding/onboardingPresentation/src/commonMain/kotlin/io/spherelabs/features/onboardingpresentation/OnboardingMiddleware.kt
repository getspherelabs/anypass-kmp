package io.spherelabs.features.onboardingpresentation

import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.onboardingdomain.IsFirstTime
import io.spherelabs.onboardingdomain.SetIsFirstTime

class OnboardingMiddleware(
    private val isFirstTime: IsFirstTime,
    private val setIsFirstTime: SetIsFirstTime
) : Middleware<OnboardingState, OnboardingWish> {

    override suspend fun process(
        state: OnboardingState,
        wish: OnboardingWish,
        next: suspend (OnboardingWish) -> Unit
    ) {
        when (wish) {
            OnboardingWish.CheckFirstTime -> {
                println("[Test] Onboarding wish starts in middleware")
                runCatching {
                    isFirstTime.execute()
                }.onSuccess {
                    println("[Test] Onboarding wish in success")
                    next.invoke(OnboardingWish.IsFinished(it))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(OnboardingWish.Failed(failureMessage))
                }
            }
            OnboardingWish.GetStartedClick -> {
                handleGetStartedClick(next)
            }
            else -> {}
        }
    }

    private suspend fun handleGetStartedClick(next: suspend (OnboardingWish) -> Unit) {
        runCatching {
            setIsFirstTime.execute(true)
        }.onSuccess {
            next.invoke(OnboardingWish.GetStarted)
        }.onFailure {
            val failureMessage = it.message ?: "Error is occurred."
            next.invoke(OnboardingWish.Failed(failureMessage))
        }
    }
}