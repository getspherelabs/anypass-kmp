package io.spherelabs.features.onboardingpresentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.store.createMeteor
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor
import io.spherelabs.meteorviewmodel.commonflow.NonNullCommonFlow
import io.spherelabs.meteorviewmodel.commonflow.NonNullCommonStateFlow
import io.spherelabs.meteorviewmodel.commonflow.asCommonFlow
import io.spherelabs.meteorviewmodel.commonflow.asCommonStateFlow
import io.spherelabs.meteorviewmodel.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class OnboardingViewModel(
    private val onboardingReducer: OnboardingReducer,
    private val onboardingMiddleware: OnboardingMiddleware
) : ViewModel() {

    var store: Store<OnboardingState, OnboardingWish, OnboardingEffect> = viewModelScope.createMeteor(
        configs = MeteorConfigs.build {
            initialState = OnboardingState.Empty
            storeName = "OnboardingViewModel"
            reducer = onboardingReducer
            middlewares = listOf(onboardingMiddleware)
        }
    )

    init {
        viewModelScope.launch {
            store.wish(OnboardingWish.CheckFirstTime)

        }
    }



    public fun wish(wish: OnboardingWish) {
        viewModelScope.launch {
            store.wish(wish)
        }
    }

    public val state: NonNullCommonStateFlow<OnboardingState> by lazy { this.store.state.asCommonStateFlow() }

    public val effect: NonNullCommonFlow<OnboardingEffect> by lazy { this.store.effect.asCommonFlow() }

    override fun onCleared() {
        super.onCleared()
        store.cancel()
    }
}