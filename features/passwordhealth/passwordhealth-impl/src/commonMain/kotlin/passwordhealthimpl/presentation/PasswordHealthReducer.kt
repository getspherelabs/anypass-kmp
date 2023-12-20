package passwordhealthimpl.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class PasswordHealthReducer :
    Reducer<PasswordHealthState, PasswordHealthWish, PasswordHealthEffect> {

    override fun reduce(
        currentState: PasswordHealthState,
        currentWish: PasswordHealthWish,
    ): Change<PasswordHealthState, PasswordHealthEffect> {
        return when (currentWish) {
            is PasswordHealthWish.PasswordStatsChanged -> {
                expect {
                    currentState.copy(
                        stats = currentWish.stats,
                    )
                }
            }
            else -> unexpected { currentState }
        }
    }
}
