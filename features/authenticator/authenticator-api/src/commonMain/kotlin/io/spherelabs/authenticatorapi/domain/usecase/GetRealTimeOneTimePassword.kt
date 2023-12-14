package io.spherelabs.authenticatorapi.domain.usecase

import io.spherelabs.authenticatorapi.model.RealTimeOtpDomain
import kotlinx.coroutines.flow.Flow

interface GetRealTimeOneTimePassword {
    fun execute(): Flow<Map<String, RealTimeOtpDomain?>>
}
