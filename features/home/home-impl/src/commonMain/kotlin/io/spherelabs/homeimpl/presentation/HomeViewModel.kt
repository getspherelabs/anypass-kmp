package io.spherelabs.homeimpl.presentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.store.createMeteor
import io.spherelabs.meteor.viewmodel.CommonViewModel

class HomeViewModel(
    private val homeReducer: HomeReducer,
    private val homeMiddleware: HomeMiddleware
) : CommonViewModel<HomeState, HomeWish, HomeEffect>() {

  override val store: Store<HomeState, HomeWish, HomeEffect> =
      viewModelScope.createMeteor(
          configs =
              MeteorConfigs.build {
                initialState = HomeState.Empty
                storeName = HOME_STORE_NAME
                reducer = homeReducer
                middlewares = listOf(homeMiddleware)
              })

  companion object {
    const val HOME_STORE_NAME = "io.spherelabs.homeimpl.presentation.HomeViewModel"
  }
}
