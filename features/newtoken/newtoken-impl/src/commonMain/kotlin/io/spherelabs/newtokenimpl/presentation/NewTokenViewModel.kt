package io.spherelabs.newtokenimpl.presentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class NewTokenViewModel(
    private val newTokenReducer: NewTokenReducer,
    private val newTokenMiddleware: NewTokenMiddleware,
) : CommonViewModel<NewTokenState, NewTokenWish, NewTokenEffect>() {

  override val store: Store<NewTokenState, NewTokenWish, NewTokenEffect> =
      createMeteor(
          configs =
              MeteorConfigs.build {
                initialState = NewTokenState.Empty
                storeName = STORE_NAME
                reducer = newTokenReducer
                middlewares = listOf(newTokenMiddleware)
              },
      )

  companion object {
    private const val STORE_NAME = "NEW_TOKEN_VIEWMODEL"
  }
}
