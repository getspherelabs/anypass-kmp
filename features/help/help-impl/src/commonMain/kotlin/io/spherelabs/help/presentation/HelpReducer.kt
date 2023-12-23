package io.spherelabs.help.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer
import io.spherelabs.model.FAQs

class HelpReducer : Reducer<HelpState, HelpWish, HelpEffect> {

    override fun reduce(
        currentState: HelpState,
        currentWish: HelpWish,
    ): Change<HelpState, HelpEffect> {
        return when (currentWish) {
            HelpWish.LoadedEmail -> currentState.loadedEmail(SUPPORT_EMAIL)
            is HelpWish.LoadedGetFaqs -> currentState.loadedFaqs(currentWish.list)
            HelpWish.NavigateToBack -> back()
            else -> unexpected { currentState }
        }
    }

    companion object {
        private const val SUPPORT_EMAIL = "teamspherelabs@gmail.com"
    }
}

fun HelpState.loadedFaqs(newList: FAQs): Change<HelpState, HelpEffect> {
    return Change(
        state = copy(list = newList),
    )
}

fun HelpState.loadedEmail(newEmail: String): Change<HelpState, HelpEffect> {
    return Change(
        state = copy(email = newEmail),
    )
}

fun back(): Change<HelpState, HelpEffect> {
    return Change(
        effect = HelpEffect.Back
    )
}
