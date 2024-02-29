package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import io.spherelabs.addnewpasswordapi.model.WebsiteDomain

sealed interface AddNewLoginWish {
    object StartLoadedWebsites : AddNewLoginWish
    data class LoadedWebsites(val data: List<WebsiteDomain>) : AddNewLoginWish
    data class OnSearchFocusChanged(val isFocus: Boolean) : AddNewLoginWish
    object OnSearchClearClicked : AddNewLoginWish
    data class OnSearchQueryChanged(val query: String) : AddNewLoginWish
    data class OnSearchLoadedWebsites(val query: String) : AddNewLoginWish
    object OnSearchingChanged : AddNewLoginWish
}
