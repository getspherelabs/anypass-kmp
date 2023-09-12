package io.spherelabs.generatepasswordpresentation

import io.spherelabs.meteor.configs.MeteorConfigs
import io.spherelabs.meteor.store.Store
import io.spherelabs.meteor.store.createMeteor
import io.spherelabs.meteor.viewmodel.CommonViewModel

class GeneratePasswordViewModel(
    private val generatePasswordReducer: GeneratePasswordReducer,
    private val generatePasswordMiddleware: GeneratePasswordMiddleware
) : CommonViewModel<GeneratePasswordState, GeneratePasswordWish, GeneratePasswordEffect>() {

    override val store: Store<GeneratePasswordState, GeneratePasswordWish, GeneratePasswordEffect> =
        viewModelScope.createMeteor(
            configs = MeteorConfigs.build {
                initialState = GeneratePasswordState.Empty
                storeName = GENERATE_PASSWORD_STORE_NAME
                reducer = generatePasswordReducer
                middlewares = listOf(generatePasswordMiddleware)
            }
        )

    companion object {
        const val GENERATE_PASSWORD_STORE_NAME = "GeneratePasswordViewModel"
    }
}