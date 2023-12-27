package io.spherelabs.authenticatorapi.domain.repository

import io.spherelabs.authenticatorapi.model.CounterDomain
import io.spherelabs.authenticatorapi.model.OtpDomain
import kotlinx.coroutines.flow.Flow

interface AuthenticatorRepository {
  fun getAllOtp(): Flow<List<OtpDomain>>

  fun getCounters(): Flow<List<CounterDomain>>

  suspend fun incrementCounter(id: String)
}
