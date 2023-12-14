package io.spherelabs.newtokenapi.domain.repository

import io.spherelabs.newtokenapi.model.NewTokenDomain

interface NewTokenRepository {
    suspend fun insertOtpWithCount(otpDomain: NewTokenDomain, counter: Long)
}

