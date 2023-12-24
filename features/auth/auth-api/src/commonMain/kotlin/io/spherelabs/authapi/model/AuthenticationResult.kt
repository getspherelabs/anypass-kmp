package io.spherelabs.authapi.model

sealed interface AuthenticationResult {
  object NotLogged : AuthenticationResult

  object Success : AuthenticationResult
}
