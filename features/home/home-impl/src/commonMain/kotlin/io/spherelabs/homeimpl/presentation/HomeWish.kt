package io.spherelabs.homeimpl.presentation

sealed interface HomeWish {
    object GetStartedCategories : HomeWish

    data class GetCategories(val categories: List<UIHomeCategory>) : HomeWish

    data class GetStartedPasswordByCategory(val categoryId: String) : HomeWish

    data class GetPasswordByCategory(val passwords: List<UIHomePassword>) : HomeWish

    data class OnPasswordsChanged(val passwords: List<UIHomePassword>) : HomeWish

    data class OnCopyClipboardChanged(val password: String) : HomeWish

    object OnVisibleChanged : HomeWish

    object NavigateToMyAccount : HomeWish

    object NavigateToGenerator : HomeWish

    object NavigateToAddNewPassword : HomeWish

    object NavigateToPasswordHealth : HomeWish

    object NavigateToAuthenticator : HomeWish

    object NavigateToHelp : HomeWish
    object ToggleHiddenVisibility : HomeWish
}
