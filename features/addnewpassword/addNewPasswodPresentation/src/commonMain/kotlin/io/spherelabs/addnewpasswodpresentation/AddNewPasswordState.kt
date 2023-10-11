package io.spherelabs.addnewpasswodpresentation

import io.spherelabs.common.Empty

data class AddNewPasswordState(
    val email: String = String.Empty,
    val password: String = String.Empty,
    val title: String = String.Empty,
    val categories: List<AddNewCategoryUi> = emptyList(),
    val currentCategory: String = String.Empty,
    val username: String = String.Empty,
    val websiteAddress: String = String.Empty,
    val notes: String = String.Empty,
    val image: String = String.Empty,
    val isEmailFailed: Boolean = false,
    val isPasswordFailed: Boolean = false,
    val isTitleFailed: Boolean = false,
    val isUserNameFailed: Boolean = false,
    val isCategoryFailed: Boolean = false,
    val isWebsiteFailed: Boolean = false,
    val isNotesFailed: Boolean = false,
    val isExpanded: Boolean = false,
    val isPasswordVisibility: Boolean = false
) {
    companion object {
        val Empty = AddNewPasswordState()
    }
}
