package io.spherelabs.authenticatorapi.domain.usecase

import io.spherelabs.authenticatorapi.model.RealTimeOtpDomain
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface GetRealTimeOneTimePassword {
    fun execute(onResult: suspend (Map<String, RealTimeOtpDomain?>) -> Unit)
    fun cancel()
}
