package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import androidx.compose.runtime.Stable
import io.spherelabs.addnewpasswordapi.model.WebsiteDomain

@Stable
data class AddNewLoginState(
    val websites: List<WebsiteDomain> = emptyList(),
) {
    companion object {
        val Empty = AddNewLoginState()
    }
}
