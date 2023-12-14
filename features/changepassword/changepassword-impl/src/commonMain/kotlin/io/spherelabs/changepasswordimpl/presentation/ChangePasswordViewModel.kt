package io.spherelabs.changepasswordimpl.presentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.store.createMeteor
import io.spherelabs.meteor.viewmodel.CommonViewModel

class ChangePasswordViewModel(
    private val changePasswordReducer: ChangePasswordReducer,
    private val changePasswordValidateMiddleware: ChangePasswordValidateMiddleware,
    private val changePasswordMiddleware: ChangePasswordMiddleware,
) : CommonViewModel<ChangePasswordState, ChangePasswordWish, ChangePasswordEffect>() {

    override val store: Store<ChangePasswordState, ChangePasswordWish, ChangePasswordEffect> =
        viewModelScope.createMeteor(
            configs =
            MeteorConfigs.build {
                initialState = ChangePasswordState.Empty
                storeName = CHANGE_PASSWORD_STORE_NAME
                reducer = changePasswordReducer
                middlewares = listOf(changePasswordMiddleware, changePasswordValidateMiddleware)
            },
        )

    companion object {
        const val CHANGE_PASSWORD_STORE_NAME = "GeneratePasswordViewModel"
    }
}
