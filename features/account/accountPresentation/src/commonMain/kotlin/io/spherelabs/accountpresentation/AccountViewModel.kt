package io.spherelabs.accountpresentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.viewmodel.CommonViewModel
import io.spherelabs.meteor.viewmodel.createMeteor

class AccountViewModel(
    private val accountReducer: AccountReducer,
    private val accountMiddleware: AccountMiddleware,
) : CommonViewModel<AccountState, AccountWish, AccountEffect>() {

    override val store: Store<AccountState, AccountWish, AccountEffect> = createMeteor(
        configs = MeteorConfigs.build {
            storeName = STORE_NAME
            initialState = AccountState.Empty
            reducer = accountReducer
            middlewares = listOf(accountMiddleware)
        },
    )

    companion object {
        private const val STORE_NAME = "AccountViewModel"
    }
}
