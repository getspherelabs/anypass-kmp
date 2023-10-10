package io.spherelabs.addnewpasswodpresentation

sealed interface AddNewPasswordWish {
    data class OnEmailChanged(val email: String) : AddNewPasswordWish
    object OnEmailFailed : AddNewPasswordWish
    data class OnPasswordChanged(val password: String) : AddNewPasswordWish
    object OnPasswordFailed : AddNewPasswordWish
    data class OnTitleChanged(val title: String) : AddNewPasswordWish
    object OnTitleFailed : AddNewPasswordWish
    data class OnCategoryChanged(val category: String) : AddNewPasswordWish
    object OnCategoryFailed : AddNewPasswordWish
    data class OnUserNameChanged(val username: String) : AddNewPasswordWish

    data class OnWebsiteAddressChanged(val websiteAddress: String) : AddNewPasswordWish
    object OnWebsiteFailed : AddNewPasswordWish
    data class OnNotesChanged(val notes: String) : AddNewPasswordWish
    object OnNotesFailed : AddNewPasswordWish
    data class OnImageChanged(val image: String) : AddNewPasswordWish
    object OnImageFailed : AddNewPasswordWish
    object OnSubmitClicked : AddNewPasswordWish

    object OnGeneratePasswordClicked : AddNewPasswordWish

    data class InsertPassword(val password: AddNewPasswordUi) : AddNewPasswordWish

    data class InsertFailed(val message: String) : AddNewPasswordWish

    data class InsertSuccess(val message: String) : AddNewPasswordWish

    object GetCategoriesStarted : AddNewPasswordWish

    data class GetCategories(val categories: List<AddNewCategoryUi>) : AddNewPasswordWish
    data class OnExpandChanged(val isExpanded: Boolean) : AddNewPasswordWish
}
