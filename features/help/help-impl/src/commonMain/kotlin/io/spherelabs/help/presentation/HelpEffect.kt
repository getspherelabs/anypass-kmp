package io.spherelabs.help.presentation

sealed interface HelpEffect {
    data class Failure(val message: String) : HelpEffect
    object Back : HelpEffect
}
