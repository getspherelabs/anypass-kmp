package io.spherelabs.home.homepresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class HomeReducer : Reducer<HomeState, HomeWish, HomeEffect> {

    override fun reduce(
        currentState: HomeState,
        currentWish: HomeWish
    ): Change<HomeState, HomeEffect> {
        return when (currentWish) {
            is HomeWish.GetCategories -> {
                expect {
                    currentState.copy(
                        categories = currentWish.categories
                    )
                }
            }
            else -> unexpected { currentState }
        }
    }
}