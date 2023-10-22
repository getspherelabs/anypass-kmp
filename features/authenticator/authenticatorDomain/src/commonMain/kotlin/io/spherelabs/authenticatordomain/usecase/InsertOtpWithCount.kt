package io.spherelabs.authenticatordomain.usecase

import io.spherelabs.authenticatordomain.model.OtpDomain
import io.spherelabs.authenticatordomain.repository.AuthenticatorRepository

interface InsertOtpWithCount {
    suspend fun execute(otpDomain: OtpDomain, counter: Long)
}

class DefaultInsertOtpWithCount(
    private val repository: AuthenticatorRepository,
) : InsertOtpWithCount {
    override suspend fun execute(otpDomain: OtpDomain, counter: Long) {
        repository.insertOtpWithCount(otpDomain, counter)
    }
}
