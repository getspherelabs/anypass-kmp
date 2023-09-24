package io.spherelabs.authpresentation.signin

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.store.createMeteor
import io.spherelabs.meteorviewmodel.commonflow.NonNullCommonFlow
import io.spherelabs.meteorviewmodel.commonflow.NonNullCommonStateFlow
import io.spherelabs.meteorviewmodel.commonflow.asCommonFlow
import io.spherelabs.meteorviewmodel.commonflow.asCommonStateFlow
import io.spherelabs.meteorviewmodel.viewmodel.ViewModel
import kotlinx.coroutines.launch


class SignInViewModel(
    private val signInReducer: SignInReducer,
    private val signInMiddleware: SignInMiddleware,
    private val signInValidateMiddleware: SignInValidateMiddleware
) : ViewModel() {

    private val store: Store<SignInState, SignInWish, SignInEffect> = viewModelScope.createMeteor(
        configs = MeteorConfigs.build {
            initialState = SignInState.Empty
            storeName = SIGN_IN_STORE_NAME
            reducer = signInReducer
            middlewares = listOf(signInMiddleware, signInValidateMiddleware)
        }
    )

    init {
        viewModelScope.launch {
            wish(SignInWish.CheckCurrentUser)
        }
    }

    fun wish(wish: SignInWish) {
        viewModelScope.launch {
            store.wish(wish)
        }
    }


    val state: NonNullCommonStateFlow<SignInState> by lazy { this.store.state.asCommonStateFlow() }

    val effect: NonNullCommonFlow<SignInEffect> by lazy { this.store.effect.asCommonFlow() }

    override fun onCleared() {
        super.onCleared()
        store.cancel()
    }

    companion object {
        private const val SIGN_IN_STORE_NAME = "SignInViewModel"
    }
}
