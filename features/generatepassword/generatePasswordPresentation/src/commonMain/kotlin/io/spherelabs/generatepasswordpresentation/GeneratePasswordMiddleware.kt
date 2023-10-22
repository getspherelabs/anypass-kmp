package io.spherelabs.generatepasswordpresentation

import io.spherelabs.generatepassworddomain.usecase.GeneratePasswordUseCase
import io.spherelabs.meteor.middleware.Middleware

class GeneratePasswordMiddleware(private val generatePasswordUseCase: GeneratePasswordUseCase) :
  Middleware<GeneratePasswordState, GeneratePasswordWish> {

  override suspend fun process(
    state: GeneratePasswordState,
    wish: GeneratePasswordWish,
    next: suspend (GeneratePasswordWish) -> Unit
  ) {
    when (wish) {
      is GeneratePasswordWish.GeneratePassword -> {
        generatePasswordUseCase
          .execute(
            uppercaseLength = wish.uppercaseLength,
            lowercaseLength = wish.lowercaseLength,
            digitLength = wish.digitLength,
            length = wish.length,
            specialLength = wish.specialLength
          )
          .onSuccess { newPassword ->
            next.invoke(GeneratePasswordWish.OnPasswordChanged(newPassword))
          }
          .onFailure {
            val failureMessage = it.message ?: "Error is occurred."

            next.invoke(GeneratePasswordWish.GeneratePasswordFailed(failureMessage))
          }
      }
      else -> {}
    }
  }
}
