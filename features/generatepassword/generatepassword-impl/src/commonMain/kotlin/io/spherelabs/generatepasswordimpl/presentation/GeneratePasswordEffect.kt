package io.spherelabs.generatepasswordimpl.presentation

sealed interface GeneratePasswordEffect {
  data class Failure(val message: String) : GeneratePasswordEffect
}
