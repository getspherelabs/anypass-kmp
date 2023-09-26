package io.spherelabs.addnewpasswodpresentation

sealed interface AddNewPasswordWish {
  data class OnEmailChanged(val email: String) : AddNewPasswordWish

  data class OnPasswordChanged(val password: String) : AddNewPasswordWish

  data class OnTitleChanged(val title: String) : AddNewPasswordWish

  data class OnCategoryChanged(val category: String) : AddNewPasswordWish

  data class OnUserNameChanged(val username: String) : AddNewPasswordWish

  data class OnWebsiteAddressChanged(val websiteAddress: String) : AddNewPasswordWish

  data class OnNotesChanged(val notes: String) : AddNewPasswordWish

  data class OnImageChanged(val image: String) : AddNewPasswordWish

  object OnSubmitClicked : AddNewPasswordWish

  object OnGeneratePasswordClicked : AddNewPasswordWish

  data class InsertPassword(val password: AddNewPasswordUi) : AddNewPasswordWish

  data class InsertFailed(val message: String) : AddNewPasswordWish

  data class InsertSuccess(val message: String) : AddNewPasswordWish

  object GetCategoriesStarted : AddNewPasswordWish

  data class GetCategories(val categories: List<AddNewCategoryUi>) : AddNewPasswordWish
}
