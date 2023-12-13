package io.spherelabs.changepasswordapi.domain.usecase


interface GetCurrentKeyPasswordUseCase {
    suspend fun execute(): String
}
