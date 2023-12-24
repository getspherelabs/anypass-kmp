package io.spherelabs.generatepasswordimpl.domain.usecase

import io.spherelabs.generatepasswordapi.domain.usecase.GeneratePasswordUseCase
import io.spherelabs.manager.password.PasswordManager

class DefaultGeneratePasswordUseCaseUseCase(private val manager: PasswordManager) :
    GeneratePasswordUseCase {

  override suspend fun execute(
      uppercaseLength: Int,
      digitLength: Int,
      lowercaseLength: Int,
      specialLength: Int,
      length: Int
  ): Result<String> {
    val result =
        manager.generate(uppercaseLength, digitLength, lowercaseLength, specialLength, length)

    return when {
      result.length < 10 -> Result.failure(Exception("The password length is less than 10."))
      result.isEmpty() -> Result.failure(Exception("The password is empty."))
      else -> Result.success(result)
    }
  }
}
