package io.spherelabs.generatepasswordapi.domain.usecase

interface GeneratePasswordUseCase {
  suspend fun execute(
    uppercaseLength: Int = 0,
    digitLength: Int = 0,
    lowercaseLength: Int = 0,
    specialLength: Int = 0,
    length: Int = 10
  ): Result<String>
}

