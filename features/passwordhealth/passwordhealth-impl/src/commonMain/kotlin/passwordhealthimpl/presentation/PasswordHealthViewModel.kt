package passwordhealthimpl.presentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class PasswordHealthViewModel(
    private val passwordHealthMiddleware: PasswordHealthMiddleware,
    private val passwordHealthReducer: PasswordHealthReducer,
) : CommonViewModel<PasswordHealthState, PasswordHealthWish, PasswordHealthEffect>() {

  override val store: Store<PasswordHealthState, PasswordHealthWish, PasswordHealthEffect> =
      createMeteor(
          configs =
              MeteorConfigs.build {
                initialState = PasswordHealthState.Empty
                storeName = STORE_NAME
                reducer = passwordHealthReducer
                middlewares = listOf(passwordHealthMiddleware)
              },
      )

  companion object {
    private const val STORE_NAME = "passwordhealthimpl.presentation.PasswordHealthViewModel"
  }
}
