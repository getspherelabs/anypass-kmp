package io.spherelabs.addnewpasswordimpl.presentation

import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.validation.EmailValidation
import io.spherelabs.validation.NameValidation
import io.spherelabs.validation.PasswordValidation
import io.spherelabs.validation.WebsiteValidation

private typealias Validation = suspend (String) -> Boolean

class AddNewPasswordValidateMiddleware(
    private val validatePassword: PasswordValidation,
    private val validateEmail: EmailValidation,
    private val validateWebsite: WebsiteValidation,
    private val validateName: NameValidation,
) : Middleware<AddNewPasswordState, AddNewPasswordWish> {

  override suspend fun process(
      state: AddNewPasswordState,
      wish: AddNewPasswordWish,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    when (wish) {
      AddNewPasswordWish.OnSubmitClicked -> {
        checkAndHandleEmail(state.email, next)
        checkAndHandlePassword(state.password, next)
        checkAndHandleTitle(state.title, next)
        checkAndHandleUserName(state.username, next)
        checkAndHandleWebsiteAddress(state.websiteAddress, next)
        checkAndHandleNotes(state.notes, next)
        checkAndHandleImages(state.image, next)
        checkAllFields(
            email = state.email,
            password = state.password,
            title = state.title,
            userName = state.username,
            websiteAddress = state.websiteAddress,
            note = state.notes,
            image = state.image,
            next = next,
        )
      }
      else -> {}
    }
  }

  private suspend fun checkAndHandleEmail(
      email: String,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    val isEmailNotValid = !validateEmail.execute(email)
    if (isEmailNotValid) {
      next.invoke(AddNewPasswordWish.OnEmailFailed)
    }
  }

  private suspend fun checkAndHandlePassword(
      password: String,
      next:
          suspend (
              AddNewPasswordWish,
          ) -> Unit,
  ) {
    val isPasswordNotValid = !validatePassword.execute(password)
    if (isPasswordNotValid) {
      next.invoke(AddNewPasswordWish.OnPasswordFailed)
    }
  }

  private suspend fun checkAndHandleTitle(
      title: String,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    val isTitleNotValid = !title.any { it.isLetterOrDigit() } && title.isEmpty()

    if (isTitleNotValid) {
      next.invoke(AddNewPasswordWish.OnTitleFailed)
    }
  }

  private suspend fun checkAndHandleUserName(
      userName: String,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    val isNameNotValid = !validateName.execute(userName)
    if (isNameNotValid) {
      next.invoke(AddNewPasswordWish.OnUserNameFailed)
    }
  }

  private suspend fun checkAndHandleWebsiteAddress(
      websiteAddress: String,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    val isWebsiteAddressNotValid = !validateWebsite.execute(websiteAddress)

    if (isWebsiteAddressNotValid) {
      next.invoke(AddNewPasswordWish.OnWebsiteFailed)
    }
  }

  private suspend fun checkAndHandleNotes(
      note: String,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    val isNoteNull = note.isEmpty()
    if (isNoteNull) {
      next.invoke(AddNewPasswordWish.OnNotesFailed)
    }
  }

  private suspend fun checkAndHandleImages(
      image: String,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    val isImageNull = image.isEmpty()
    if (isImageNull) {
      next.invoke(AddNewPasswordWish.OnImageFailed)
    }
  }

  private suspend fun checkAllFields(
      email: String,
      password: String,
      userName: String,
      title: String,
      websiteAddress: String,
      note: String,
      image: String,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    suspend fun isValid(value: String, validation: Validation) =
        value.isNotEmpty() && validation.invoke(value)

    val isEmailValid = isValid(email, validateEmail::execute)
    val isPasswordValid = isValid(password, validatePassword::execute)
    val isUserNameValid = isValid(userName, validateName::execute)
    val isTitleValid = title.any { it.isLetterOrDigit() }
    val isWebsiteAddressValid = isValid(websiteAddress, validateWebsite::execute)
    val isNoteValid = note.isNotEmpty()
    val isImageValid = image.isNotEmpty()

    if (isEmailValid &&
        isPasswordValid &&
        isUserNameValid &&
        isTitleValid &&
        isWebsiteAddressValid &&
        isNoteValid &&
        isImageValid) {
      next.invoke(AddNewPasswordWish.AddNewPassword)
    }
  }
}
