package io.spherelabs.home.homepresentation

sealed interface HomeEffect {
    data class Failure(val message: String) : HomeEffect

    data class CopyClipboard(val message: String) : HomeEffect
    object NavigateToGenerator : HomeEffect
    object NavigateToMyAccount : HomeEffect
    object NavigateToAddNewPassword : HomeEffect
    object NavigateToPasswordHealth : HomeEffect
    object NavigateToAuthenticator : HomeEffect
    object NavigateToHelp : HomeEffect
}
