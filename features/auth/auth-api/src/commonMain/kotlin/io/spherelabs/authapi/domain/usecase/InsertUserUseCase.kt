package io.spherelabs.authapi.domain.usecase

interface InsertUserUseCase {
    suspend fun execute(name: String, email: String, password: String)
}
