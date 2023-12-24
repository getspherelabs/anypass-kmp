package io.spherelabs.help.presentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class HelpViewModel(
    private val helpReducer: HelpReducer,
    private val helpMiddleware: HelpMiddleware,
) : CommonViewModel<HelpState, HelpWish, HelpEffect>() {

  override val store: Store<HelpState, HelpWish, HelpEffect> =
      createMeteor(
          configs =
              MeteorConfigs.build {
                initialState = HelpState.Empty
                storeName = STORE_NAME
                reducer = helpReducer
                middlewares = listOf(helpMiddleware)
              },
      )

  companion object {
    private const val STORE_NAME = "io.spherelabs.help.presentation.HelpViewModel"
  }
}
