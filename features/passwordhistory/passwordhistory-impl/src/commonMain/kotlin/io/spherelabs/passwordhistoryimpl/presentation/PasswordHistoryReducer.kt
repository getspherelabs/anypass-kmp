package io.spherelabs.passwordhistoryimpl.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class PasswordHistoryReducer :
    Reducer<PasswordHistoryState, PasswordHistoryWish, PasswordHistoryEffect> {

    override fun reduce(
        currentState: PasswordHistoryState,
        currentWish: PasswordHistoryWish,
    ): Change<PasswordHistoryState, PasswordHistoryEffect> {
        return when (currentWish) {
            is PasswordHistoryWish.Failure -> {
                effect { PasswordHistoryEffect.Failure(currentWish.message) }
            }
            is PasswordHistoryWish.LoadedPasswordHistory -> {
                currentState.loaded { currentWish.data }
            }
            PasswordHistoryWish.OnToggleVisibility -> {
                currentState.toggled()
            }
            else -> unexpected { currentState }
        }
    }
}

fun PasswordHistoryState.loaded(block: () -> List<UiPasswordHistory>): Change<PasswordHistoryState, PasswordHistoryEffect> {
    return Change(
        state = this.copy(history = block.invoke()),
    )
}

fun PasswordHistoryState.toggled(): Change<PasswordHistoryState, PasswordHistoryEffect> {
    return Change(
        state = this.copy(isPasswordHidden = !this.isPasswordHidden),
    )
}
