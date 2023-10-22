package io.spherelabs.authenticatordomain.repository

import io.spherelabs.authenticatordomain.model.CounterDomain
import io.spherelabs.authenticatordomain.model.OtpDomain
import kotlinx.coroutines.flow.Flow

interface AuthenticatorRepository {
    fun getAllOtp(): Flow<List<OtpDomain>>
    fun getCounters(): Flow<List<CounterDomain>>
    suspend fun insertOtpWithCount(otpDomain: OtpDomain, counter: Long)
    suspend fun incrementCounter(id: String)
}
