package io.spherelabs.addnewpasswodpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class AddNewPasswordReducer :
  Reducer<AddNewPasswordState, AddNewPasswordWish, AddNewPasswordEffect> {

  override fun reduce(
    currentState: AddNewPasswordState,
    currentWish: AddNewPasswordWish
  ): Change<AddNewPasswordState, AddNewPasswordEffect> {
    return when (currentWish) {
      is AddNewPasswordWish.InsertFailed -> {
        effect { AddNewPasswordEffect.Failure(currentWish.message) }
      }
      is AddNewPasswordWish.InsertSuccess -> {
        effect { AddNewPasswordEffect.Success(currentWish.message) }
      }
      is AddNewPasswordWish.OnCategoryChanged -> {
        expect { currentState.copy(category = currentWish.category) }
      }
      is AddNewPasswordWish.OnEmailChanged -> {
        expect { currentState.copy(email = currentWish.email) }
      }
      is AddNewPasswordWish.OnImageChanged -> {
        expect { currentState.copy(image = currentWish.image) }
      }
      is AddNewPasswordWish.OnNotesChanged -> {
        expect { currentState.copy(notes = currentWish.notes) }
      }
      is AddNewPasswordWish.OnPasswordChanged -> {
        expect { currentState.copy(password = currentWish.password) }
      }
      is AddNewPasswordWish.OnTitleChanged -> {
        expect { currentState.copy(title = currentWish.title) }
      }
      is AddNewPasswordWish.OnUserNameChanged -> {
        expect { currentState.copy(username = currentWish.username) }
      }
      is AddNewPasswordWish.OnWebsiteAddressChanged -> {
        expect { currentState.copy(websiteAddress = currentWish.websiteAddress) }
      }
      AddNewPasswordWish.OnGeneratePasswordClicked -> {
        route { AddNewPasswordEffect.GeneratePassword }
      }
      else -> unexpected { currentState }
    }
  }
}
