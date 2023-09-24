package io.spherelabs.masterpasswordpresentation

sealed interface MasterPasswordEffect {
    data class Failure(val message: String) : MasterPasswordEffect
    object Home : MasterPasswordEffect
}