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
        currentWish: AddNewPasswordWish,
    ): Change<AddNewPasswordState, AddNewPasswordEffect> {
        return when (currentWish) {
            is AddNewPasswordWish.InsertFailed -> {
                effect { AddNewPasswordEffect.Failure(currentWish.message) }
            }

            is AddNewPasswordWish.InsertSuccess -> {
                effect { AddNewPasswordEffect.Success(currentWish.message) }
            }

            is AddNewPasswordWish.OnCategoryChanged,
            is AddNewPasswordWish.OnEmailChanged,
            is AddNewPasswordWish.OnNotesChanged,
            is AddNewPasswordWish.OnPasswordChanged,
            is AddNewPasswordWish.OnTitleChanged,
            is AddNewPasswordWish.OnUserNameChanged,
            is AddNewPasswordWish.OnWebsiteAddressChanged,
            is AddNewPasswordWish.OnImageChanged,
            AddNewPasswordWish.ToggleVisibility,
            -> {
                handleChanges(currentState, currentWish)
            }
            is AddNewPasswordWish.OnEmailFailed,
            is AddNewPasswordWish.OnPasswordFailed,
            is AddNewPasswordWish.OnWebsiteFailed,
            is AddNewPasswordWish.OnCategoryFailed,
            is AddNewPasswordWish.OnNotesFailed,
            is AddNewPasswordWish.OnTitleFailed,
            is AddNewPasswordWish.OnUserNameFailed,
            is AddNewPasswordWish.OnImageFailed,
            -> {
                handleFailures(currentState, currentWish)
            }

            AddNewPasswordWish.OnGeneratePasswordClicked -> {
                route { AddNewPasswordEffect.GeneratePassword }
            }

            is AddNewPasswordWish.GetCategories -> {
                val categories = currentWish.categories
                expect { currentState.copy(categories = categories) }
            }

            is AddNewPasswordWish.OnExpandChanged -> {
                expect { currentState.copy(isExpanded = currentWish.isExpanded) }
            }

            else -> unexpected { currentState }
        }
    }

    private fun handleChanges(
        currentState: AddNewPasswordState,
        currentWish: AddNewPasswordWish,
    ): Change<AddNewPasswordState, AddNewPasswordEffect> {
        return when (currentWish) {
            is AddNewPasswordWish.OnCategoryChanged -> {
                expect {
                    currentState.copy(
                        currentCategory = currentWish.category,
                        isCategoryFailed = false,
                    )
                }
            }

            is AddNewPasswordWish.OnEmailChanged -> {
                expect { currentState.copy(email = currentWish.email.trim(), isEmailFailed = false) }
            }

            is AddNewPasswordWish.OnImageChanged -> {
                expect { currentState.copy(image = currentWish.image) }
            }

            is AddNewPasswordWish.OnNotesChanged -> {
                expect {
                    currentState.copy(
                        notes = currentWish.notes.trim(), isNotesFailed = false,
                    )
                }
            }

            is AddNewPasswordWish.OnPasswordChanged -> {
                expect {
                    currentState.copy(
                        password = currentWish.password?.trim() ?: "",
                        isPasswordFailed = false,
                    )
                }
            }

            is AddNewPasswordWish.OnTitleChanged -> {
                expect { currentState.copy(title = currentWish.title, isTitleFailed = false) }
            }

            is AddNewPasswordWish.OnUserNameChanged -> {
                expect {
                    currentState.copy(
                        username = currentWish.username.trim(),
                        isUserNameFailed = false,
                    )
                }
            }

            is AddNewPasswordWish.OnWebsiteAddressChanged -> {
                expect {
                    currentState.copy(
                        websiteAddress = currentWish.websiteAddress.trim(), isWebsiteFailed = false,
                    )
                }
            }

            AddNewPasswordWish.ToggleVisibility -> {
                expect {
                    currentState.copy(
                        isPasswordVisibility = !currentState.isPasswordVisibility,
                    )
                }
            }

            else -> {
                unexpected { currentState }
            }
        }
    }

    private fun handleFailures(
        currentState: AddNewPasswordState,
        currentWish: AddNewPasswordWish,
    ): Change<AddNewPasswordState, AddNewPasswordEffect> {
        return when (currentWish) {
            is AddNewPasswordWish.OnEmailFailed -> {
                expect { currentState.copy(isEmailFailed = true) }
            }

            is AddNewPasswordWish.OnPasswordFailed -> {
                expect { currentState.copy(isPasswordFailed = true) }
            }

            is AddNewPasswordWish.OnWebsiteFailed -> {
                expect { currentState.copy(isWebsiteFailed = true) }
            }

            is AddNewPasswordWish.OnCategoryFailed -> {
                expect { currentState.copy(isCategoryFailed = true) }
            }

            is AddNewPasswordWish.OnNotesFailed -> {
                expect { currentState.copy(isNotesFailed = true) }
            }

            is AddNewPasswordWish.OnTitleFailed -> {
                expect { currentState.copy(isTitleFailed = true) }
            }

            is AddNewPasswordWish.OnUserNameFailed -> {
                expect { currentState.copy(isUserNameFailed = true) }
            }

            is AddNewPasswordWish.OnImageFailed -> {
                effect { AddNewPasswordEffect.Failure("Please, select a icon for your password!") }
            }

            else -> unexpected { currentState }
        }
    }
}
