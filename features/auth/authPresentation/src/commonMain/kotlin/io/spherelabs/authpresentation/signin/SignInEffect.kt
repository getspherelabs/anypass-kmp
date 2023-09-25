package io.spherelabs.authpresentation.signin

sealed interface SignInEffect {
  data class Failure(val message: String) : SignInEffect

  object SignUp : SignInEffect

  object Discover : SignInEffect
}
