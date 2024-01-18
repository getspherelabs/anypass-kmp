package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import androidx.compose.runtime.Stable
import io.spherelabs.addnewpasswordapi.model.WebsiteDomain

@Stable
data class AddNewLoginState(
    val websites: List<WebsiteDomain> = emptyList(),
    val filteredWebsites: List<WebsiteDomain> = emptyList(),
    val unfilteredWebsites: List<WebsiteDomain> = emptyList(),
    val query: String = "",
    val isSearchFocused: Boolean = false,
    val isSearched: Boolean = false,
    val searchType: SearchType = SearchType.NotSearch,
) {
    companion object {
        val Empty = AddNewLoginState()
    }
}

enum class SearchType {
    Searching,
    NotSearch
}
