package io.spherelabs.authpresentation.signin

sealed interface SignInEffect {
  data class Failure(val message: String) : SignInEffect

  object CreateNew : SignInEffect

  object KeyPassword : SignInEffect
}
