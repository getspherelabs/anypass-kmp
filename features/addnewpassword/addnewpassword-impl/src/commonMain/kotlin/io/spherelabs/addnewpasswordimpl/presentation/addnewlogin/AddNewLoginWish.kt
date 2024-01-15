package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import io.spherelabs.addnewpasswordapi.model.WebsiteDomain

sealed interface AddNewLoginWish {
    object StartLoadedWebsites : AddNewLoginWish
    data class LoadedWebsites(val data: List<WebsiteDomain>) : AddNewLoginWish
}
