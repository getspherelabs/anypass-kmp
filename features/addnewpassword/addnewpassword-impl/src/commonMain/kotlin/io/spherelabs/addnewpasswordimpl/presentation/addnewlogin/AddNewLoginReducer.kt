package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import io.spherelabs.addnewpasswordapi.model.WebsiteDomain
import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class AddNewLoginReducer : Reducer<AddNewLoginState, AddNewLoginWish, AddNewLoginEffect> {

    override fun reduce(
        currentState: AddNewLoginState,
        currentWish: AddNewLoginWish,
    ): Change<AddNewLoginState, AddNewLoginEffect> {
        return when (currentWish) {
            is AddNewLoginWish.LoadedWebsites -> {
                expect {
                    currentState.copy(
                        websites = currentWish.data.take(12),
                        unfilteredWebsites = currentWish.data,
                    )
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
            is AddNewLoginWish.OnSearchLoadedWebsites -> {
                println("Current query is ${currentWish.query}")
                val filteredWebsites =
                    if (currentWish.query.isEmpty()) emptyList() else {
                        currentState.unfilteredWebsites.filter { it.contains(currentWish.query) }
                    }

                println("Filtered websites $filteredWebsites")

                expect {
                    currentState.copy(
                        filteredWebsites = filteredWebsites,
                        isSearched = true,
                    )
                }

            }
            AddNewLoginWish.OnSearchingChanged -> {
                expect { currentState.copy(isSearched = false) }
            }
            else -> unexpected { currentState }
        }
    }
}
