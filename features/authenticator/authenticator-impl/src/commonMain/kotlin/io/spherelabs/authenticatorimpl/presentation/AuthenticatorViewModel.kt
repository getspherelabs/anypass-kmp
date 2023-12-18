package io.spherelabs.authenticatorimpl.presentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class AuthenticatorViewModel(
    private val authenticatorMiddleware: AuthenticatorMiddleware,
    private val authenticatorReducer: AuthenticatorReducer,
) : CommonViewModel<AuthenticatorState, AuthenticatorWish, AuthenticatorEffect>() {

    override val store: Store<AuthenticatorState, AuthenticatorWish, AuthenticatorEffect> =
        createMeteor(
            configs = MeteorConfigs.build {
                initialState = AuthenticatorState.Empty
                storeName = STORE_NAME
                reducer = authenticatorReducer
                middlewares = listOf(authenticatorMiddleware)
            },
        )
    companion object {
        private const val STORE_NAME =
            "io.spherelabs.authenticatorimpl.presentation.AuthenticatorViewModel"
    }

    override fun onCleared() {
        super.onCleared()
    }
}
