package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer
import kotlinx.coroutines.coroutineScope

class AddNewLoginReducer : Reducer<AddNewLoginState, AddNewLoginWish, AddNewLoginEffect> {

    override fun reduce(
        currentState: AddNewLoginState,
        currentWish: AddNewLoginWish,
    ): Change<AddNewLoginState, AddNewLoginEffect> {
        return when (currentWish) {
            is AddNewLoginWish.LoadedWebsites -> {
                expect {
                    currentState.copy(websites = currentWish.data)
                }
            }
            is AddNewLoginWish.OnSearchFocusChanged -> {
                expect {
                    currentState.copy(
                        isSearchFocused = currentWish.isFocus,
                    )
                }
            }
            is AddNewLoginWish.OnSearchQueryChanged -> {
                expect {
                    currentState.copy(
                        query = currentWish.query,
                    )
                }
            }
            else -> unexpected { currentState }
        }
    }
}
