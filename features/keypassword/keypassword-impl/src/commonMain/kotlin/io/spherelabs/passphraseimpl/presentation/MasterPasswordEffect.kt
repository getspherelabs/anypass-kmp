package io.spherelabs.passphraseimpl.presentation

sealed interface MasterPasswordEffect {
  data class Failure(val message: String) : MasterPasswordEffect

  object Home : MasterPasswordEffect
}
