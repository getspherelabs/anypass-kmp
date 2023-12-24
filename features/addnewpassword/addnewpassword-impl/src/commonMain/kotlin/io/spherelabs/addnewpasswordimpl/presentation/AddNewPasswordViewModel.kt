package io.spherelabs.addnewpasswordimpl.presentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.store.createMeteor
import io.spherelabs.meteor.viewmodel.CommonViewModel

class AddNewPasswordViewModel(
    private val addNewPasswordReducer: AddNewPasswordReducer,
    private val addNewPasswordMiddleware: AddNewPasswordMiddleware,
    private val addNewPasswordValidateMiddleware: AddNewPasswordValidateMiddleware,
) : CommonViewModel<AddNewPasswordState, AddNewPasswordWish, AddNewPasswordEffect>() {

  override val store: Store<AddNewPasswordState, AddNewPasswordWish, AddNewPasswordEffect> =
      viewModelScope.createMeteor(
          configs =
              MeteorConfigs.build {
                initialState = AddNewPasswordState.Empty
                storeName = ADD_NEW_PASSWORD_STORE_NAME
                reducer = addNewPasswordReducer
                middlewares = listOf(addNewPasswordMiddleware, addNewPasswordValidateMiddleware)
              },
      )

  companion object {
    private const val ADD_NEW_PASSWORD_STORE_NAME = "AddNewPasswordViewModel"
  }
}
