package io.spherelabs.help.presentation

import io.spherelabs.model.FAQs

sealed interface HelpWish {
    object StartLoadingGetFaqs : HelpWish
    data class LoadedGetFaqs(val list: FAQs) : HelpWish
    object LoadedEmail : HelpWish
    object NavigateToBack: HelpWish
}
