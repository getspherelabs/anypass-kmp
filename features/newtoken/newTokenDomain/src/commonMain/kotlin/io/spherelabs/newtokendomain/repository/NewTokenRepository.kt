package io.spherelabs.newtokendomain.repository

import io.spherelabs.newtokendomain.model.NewTokenDomain

interface NewTokenRepository {
    suspend fun insertOtpWithCount(otpDomain: NewTokenDomain, counter: Long)
}
