package io.spherelabs.newtokenapi.domain.usecase

import io.spherelabs.newtokenapi.model.NewTokenDomain

interface InsertOtpWithCountUseCase {
    suspend fun execute(data: NewTokenDomain, count: Long)
}
