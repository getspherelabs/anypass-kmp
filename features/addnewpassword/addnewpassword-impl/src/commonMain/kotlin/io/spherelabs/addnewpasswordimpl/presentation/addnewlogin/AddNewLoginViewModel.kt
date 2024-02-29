package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class AddNewLoginViewModel(
    private val addNewLoginReducer: AddNewLoginReducer,
    private val addNewLoginMiddleware: AddNewLoginMiddleware,
) : CommonViewModel<AddNewLoginState, AddNewLoginWish, AddNewLoginEffect>() {

    override val store: Store<AddNewLoginState, AddNewLoginWish, AddNewLoginEffect> = createMeteor(
        configs = MeteorConfigs.build {
            initialState = AddNewLoginState.Empty
            storeName = STORE_NAME
            reducer = addNewLoginReducer
            middlewares = listOf(addNewLoginMiddleware)
        },
    )

    companion object {
        private const val STORE_NAME =
            "io.spherelabs.addnewpasswordimpl.presentation.addnewlogin.AddNewLoginViewModel"
    }
}
