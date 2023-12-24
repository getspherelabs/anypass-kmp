package io.spherelabs.addnewpasswordimpl.presentation

import io.spherelabs.addnewpasswordapi.domain.usecase.AddNewPasswordUseCase
import io.spherelabs.addnewpasswordapi.domain.usecase.GetCategoriesUseCase
import io.spherelabs.common.uuid4
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class AddNewPasswordMiddleware(
    private val addNewPasswordUseCase: AddNewPasswordUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : Middleware<AddNewPasswordState, AddNewPasswordWish> {

  override suspend fun process(
      state: AddNewPasswordState,
      wish: AddNewPasswordWish,
      next: suspend (AddNewPasswordWish) -> Unit,
  ) {
    when (wish) {
      is AddNewPasswordWish.InsertPassword -> {
        runCatching { addNewPasswordUseCase.execute(wish.password.asDomain()) }
            .onSuccess { next.invoke(AddNewPasswordWish.InsertSuccess("Successfully added!")) }
            .onFailure {
              val failureMessage = it.message ?: "Error is occurred."
              next.invoke(AddNewPasswordWish.InsertFailed(failureMessage))
            }
      }
      // Just don't need?
      AddNewPasswordWish.AddNewPassword -> {
        val currentCategory = state.categories.find { it.title == state.currentCategory }?.id ?: "0"

        next.invoke(
            AddNewPasswordWish.InsertPassword(
                AddNewPasswordUi(
                    id = uuid4(),
                    title = state.title,
                    password = state.password,
                    websiteAddress = state.websiteAddress,
                    username = state.username,
                    notes = state.notes,
                    image = state.image,
                    email = state.email,
                    category = currentCategory,
                ),
            ),
        )
      }
      AddNewPasswordWish.GetCategoriesStarted -> {
        getCategoriesUseCase.execute().collectLatest { newCategories ->
          if (newCategories.isNotEmpty()) {
            next.invoke(AddNewPasswordWish.GetCategories(newCategories.map { it.asUi() }))
          }
        }
      }
      else -> {}
    }
  }
}
