package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import androidx.compose.runtime.Stable
import io.spherelabs.addnewpasswordapi.model.WebsiteDomain

@Stable
data class AddNewLoginState(
    val websites: List<UIWebsite> = emptyList(),
    val filteredWebsites: List<UIWebsite> = emptyList(),
    val unfilteredWebsites: List<UIWebsite> = emptyList(),
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
