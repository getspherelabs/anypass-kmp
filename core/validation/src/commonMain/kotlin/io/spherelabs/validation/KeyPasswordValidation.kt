package io.spherelabs.validation

interface KeyPasswordValidation {
  suspend fun execute(keyPassword: String): Boolean
}

class DefaultKeyPasswordValidation : KeyPasswordValidation {
  override suspend fun execute(keyPassword: String): Boolean {
    return keyPassword.length in 0..4 && keyPassword.all { it.isDigit() }
  }
}
