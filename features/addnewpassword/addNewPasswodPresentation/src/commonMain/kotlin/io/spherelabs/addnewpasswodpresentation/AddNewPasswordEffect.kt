package io.spherelabs.addnewpasswodpresentation


sealed interface AddNewPasswordEffect {
    data class Failure(val message: String) : AddNewPasswordEffect
    data class Success(val message: String) : AddNewPasswordEffect
    object GeneratePassword : AddNewPasswordEffect
}