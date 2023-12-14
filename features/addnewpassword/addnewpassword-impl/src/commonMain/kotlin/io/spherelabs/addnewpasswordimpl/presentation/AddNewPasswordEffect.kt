package io.spherelabs.addnewpasswordimpl.presentation

sealed interface AddNewPasswordEffect {
  data class Failure(val message: String) : AddNewPasswordEffect
  data class Success(val message: String) : AddNewPasswordEffect
  object GeneratePassword : AddNewPasswordEffect
}
