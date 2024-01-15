package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin


sealed interface AddNewLoginEffect {
    data class Failure(val message: String): AddNewLoginEffect
}
