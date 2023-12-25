package io.spherelabs.accountapi.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetRestrictScreenshotUseCase {
    fun execute(): Flow<Boolean>
}
