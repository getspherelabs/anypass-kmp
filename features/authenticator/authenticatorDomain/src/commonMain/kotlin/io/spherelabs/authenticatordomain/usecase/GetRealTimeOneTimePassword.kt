package io.spherelabs.authenticatordomain.usecase

import io.spherelabs.authenticatordomain.model.CounterDomain
import io.spherelabs.authenticatordomain.model.OtpDomain
import io.spherelabs.authenticatordomain.model.RealTimeOtpDomain
import io.spherelabs.authenticatordomain.model.asManager
import io.spherelabs.authenticatordomain.repository.AuthenticatorRepository
import io.spherelabs.otp.OtpConfiguration
import io.spherelabs.otp.OtpManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transformLatest

interface GetRealTimeOneTimePassword {
    fun execute(): Flow<Map<String, RealTimeOtpDomain?>>
}

class DefaultGetRealTimeOneTimePassword(
    private val repository: AuthenticatorRepository,
    private val manager: OtpManager,
) : GetRealTimeOneTimePassword {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(): Flow<Map<String, RealTimeOtpDomain?>> {
        return combine(repository.getAllOtp(), repository.getCounters()) { otp, counter ->
            Pair(
                otp.associateBy { it.id },
                counter.associateBy { it.otpId },
            )
        }.transformLatest { (otp, counter) ->
            while (true) {
                val realTimeOtp =
                    generateRealTimeOneTimePassword(otpContainer = otp, counterContainer = counter)
                emit(realTimeOtp)
                delay(UPDATE_INTERVAL_MS)
            }

        }

    }

    private suspend fun generateRealTimeOneTimePassword(
        otpContainer: OtpContainer,
        counterContainer: CounterContainer,
    ): Map<String, RealTimeOtpDomain?> {
        return otpContainer.mapValues { (id, otpResult) ->
            counterContainer[id]?.counter?.let { newCounter ->
                RealTimeOtpDomain(
                    code = manager.generate(
                        count = newCounter,
                        secret = otpResult.secret,
                        configuration = OtpConfiguration(
                            period = otpResult.duration.asManager(),
                            digits = otpResult.digit.asManager(),
                            algorithmType = otpResult.type.asManager(),
                        ),
                    ),
                    count = newCounter,
                )
            }

        }
    }

    companion object {
        private const val UPDATE_INTERVAL_MS = 1000L
    }
}

typealias OtpContainer = Map<String, OtpDomain>
typealias CounterContainer = Map<String, CounterDomain>