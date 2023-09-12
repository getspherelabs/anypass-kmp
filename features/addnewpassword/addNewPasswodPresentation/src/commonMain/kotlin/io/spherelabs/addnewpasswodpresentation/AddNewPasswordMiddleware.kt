package io.spherelabs.addnewpasswodpresentation

import com.benasher44.uuid.uuid4
import io.spherelabs.addnewpassworddomain.usecase.AddNewPassword
import io.spherelabs.meteor.middleware.Middleware

class AddNewPasswordMiddleware(
    private val addNewPasswordUseCase: AddNewPassword
) : Middleware<AddNewPasswordState, AddNewPasswordWish> {

    override suspend fun process(
        state: AddNewPasswordState,
        wish: AddNewPasswordWish,
        next: suspend (AddNewPasswordWish) -> Unit
    ) {
        when (wish) {
            is AddNewPasswordWish.InsertPassword -> {
                runCatching {
                    addNewPasswordUseCase.execute(
                        wish.password.asDomain()
                    )
                }.onSuccess {
                    next.invoke(AddNewPasswordWish.InsertSuccess("Successfully added!"))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(AddNewPasswordWish.InsertFailed(failureMessage))
                }
            }
            is AddNewPasswordWish.OnSubmitClicked -> {
                next.invoke(
                    AddNewPasswordWish.InsertPassword(
                        AddNewPasswordUi(
                            id =  uuid4().toString(),
                            title = state.title,
                            password = state.password,
                            websiteAddress = state.websiteAddress,
                            username = state.username,
                            notes = state.notes,
                            image = state.image,
                            email = state.email,
                            category = null
                        )
                    )
                )
            }
            else -> {}
        }
    }
}