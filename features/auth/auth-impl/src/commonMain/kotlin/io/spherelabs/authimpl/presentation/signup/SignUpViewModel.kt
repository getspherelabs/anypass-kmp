package io.spherelabs.authimpl.presentation.signup

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class SignUpViewModel(
    private val signUpReducer: SignUpReducer,
    private val signUpValidateMiddleware: SignUpValidateMiddleware,
    private val signUpMiddleware: SignUpMiddleware
) : CommonViewModel<SignUpState, SignUpWish, SignUpEffect>() {

  override val store: Store<SignUpState, SignUpWish, SignUpEffect> =
    createMeteor(
      configs =
        MeteorConfigs.build {
          initialState = SignUpState.Empty
          storeName = SIGN_UP_STORE_NAME
          reducer = signUpReducer
          middlewares = listOf(signUpValidateMiddleware, signUpMiddleware)
        }
    )

  companion object {
    private const val SIGN_UP_STORE_NAME = "SignInViewModel"
  }
}
