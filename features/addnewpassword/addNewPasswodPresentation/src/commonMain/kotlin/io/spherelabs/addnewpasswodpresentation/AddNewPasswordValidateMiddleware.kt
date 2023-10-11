package io.spherelabs.addnewpasswodpresentation

import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.validation.EmailValidation
import io.spherelabs.validation.NameValidation
import io.spherelabs.validation.PasswordValidation
import io.spherelabs.validation.WebsiteValidation

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
        next: suspend (
            AddNewPasswordWish,
        ) -> Unit,
    ) {
        val isPasswordNotValid =
            !validatePassword.execute(password)
        if (isPasswordNotValid) {
            next.invoke(AddNewPasswordWish.OnPasswordFailed)
        }
    }

    private suspend fun checkAndHandleTitle(
        title: String,
        next: suspend (AddNewPasswordWish) -> Unit,
    ) {
        val isTitleNotValid = !title.all { it.isLetterOrDigit() }

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
}
