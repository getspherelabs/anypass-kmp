package io.spherelabs.accountapi.domain.usecase

interface LogoutUseCase {
    suspend fun execute(): Result<Boolean>
}
