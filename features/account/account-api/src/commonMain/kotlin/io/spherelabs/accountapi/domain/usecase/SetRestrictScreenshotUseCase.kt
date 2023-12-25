package io.spherelabs.accountapi.domain.usecase

interface SetRestrictScreenshotUseCase {
    suspend fun execute(newValue: Boolean)
}
