package io.spherelabs.authpresentation.signup

sealed interface SignUpEffect {
  data class Failure(val message: String) : SignUpEffect

  object Back : SignUpEffect

  object AddPrivatePassword : SignUpEffect
}
