package io.spherelabs.authapi.domain.usecase

interface CreateEmailAndPassword {
  suspend fun execute(
      name: String,
      email: String,
      password: String,
      keyPassword: String,
  ): Result<String>
}
