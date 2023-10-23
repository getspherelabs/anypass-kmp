package io.spherelabs.newtokendomain.usecase

import io.spherelabs.newtokendomain.model.NewTokenDomain
import io.spherelabs.newtokendomain.repository.NewTokenRepository

interface InsertOtpWithCountUseCase {
    suspend fun execute(data: NewTokenDomain, count: Long)
}

class DefaultInsertOtpWithCount(
    private val repository: NewTokenRepository,
) : InsertOtpWithCountUseCase {
    override suspend fun execute(otpDomain: NewTokenDomain, counter: Long) {
        repository.insertOtpWithCount(otpDomain, counter)
    }
}
