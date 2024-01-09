package io.spherelabs.passwordhistoryimpl.presentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class PasswordHistoryViewModel(
    private val passwordHistoryReducer: PasswordHistoryReducer,
    private val passwordHistoryMiddleware: PasswordHistoryMiddleware,
) : CommonViewModel<PasswordHistoryState, PasswordHistoryWish, PasswordHistoryEffect>() {

    override val store: Store<PasswordHistoryState, PasswordHistoryWish, PasswordHistoryEffect> =
        createMeteor(
            configs = MeteorConfigs.build {
                initialState = PasswordHistoryState.Empty
                storeName = STORE_NAME
                reducer = passwordHistoryReducer
                middlewares = listOf(passwordHistoryMiddleware)
            },
        )

    companion object {
        private const val STORE_NAME =
            "io.spherelabs.passwordhistoryimpl.presentation.PasswordHistoryViewModel"
    }
}
