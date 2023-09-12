package io.spherelabs.generatepasswordpresentation

sealed interface GeneratePasswordEffect {
    data class Failure(val message: String) : GeneratePasswordEffect
}