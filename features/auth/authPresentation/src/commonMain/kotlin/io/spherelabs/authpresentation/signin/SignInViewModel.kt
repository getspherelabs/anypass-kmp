package io.spherelabs.authpresentation.signin

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor


class SignInViewModel(
    private val signInReducer: SignInReducer,
    private val signInMiddleware: SignInMiddleware,
    private val signInValidateMiddleware: SignInValidateMiddleware
) : CommonViewModel<SignInState, SignInWish, SignInEffect>() {

    override val store: Store<SignInState, SignInWish, SignInEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = SignInState.Empty
            storeName = SIGN_IN_STORE_NAME
            reducer = signInReducer
            middlewares = listOf(signInMiddleware, signInValidateMiddleware)
        }
    )

    companion object {
        private const val SIGN_IN_STORE_NAME = "SignInViewModel"
    }
}
