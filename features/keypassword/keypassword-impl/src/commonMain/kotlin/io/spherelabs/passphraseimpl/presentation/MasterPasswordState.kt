package io.spherelabs.passphraseimpl.presentation

data class MasterPasswordState(
    val password: String = "",
    val isInitialPasswordExisted: Boolean = false,
    val isExistPassword: Boolean = false,
    val isFingerprintEnabled: Boolean = true,
) {
  companion object {
    const val MAX_LENGTH_OF_KEY_PASSWORD = 4
    val Empty = MasterPasswordState()
  }
}
