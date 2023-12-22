package io.spherelabs.authenticatorimpl.domain.usecase

import io.spherelabs.authenticatorapi.domain.repository.AuthenticatorRepository
import io.spherelabs.authenticatorapi.domain.usecase.GetRealTimeOneTimePassword
import io.spherelabs.authenticatorapi.model.CounterDomain
import io.spherelabs.authenticatorapi.model.OtpDomain
import io.spherelabs.authenticatorapi.model.RealTimeOtpDomain
import io.spherelabs.authenticatorimpl.mapper.asManager
import io.spherelabs.otp.OtpConfiguration
import io.spherelabs.otp.OtpManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


class DefaultGetRealTimeOneTimePassword(
    private val repository: AuthenticatorRepository,
    private val manager: OtpManager,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) : GetRealTimeOneTimePassword {

    private var currentJob: Job? = null

    override fun execute(onResult: suspend (Map<String, RealTimeOtpDomain?>) -> Unit) {
        currentJob?.cancel()
        currentJob = coroutineScope.launch {
            repository.getAllOtp().combine(repository.getCounters()) { otp, counter ->
                Pair(
                    otp.associateBy { it.id },
                    counter.associateBy { it.otpId },
                )
            }.collectLatest { (otp, counter) ->
                while (isActive) {
                    val realTimeOtp = generateRealTimeOneTimePassword(otp, counter)
                    onResult(realTimeOtp)
                    delay(UPDATE_INTERVAL_MS)
                }
            }
        }
    }

    override fun cancel() {
        currentJob?.cancel()
    }

    private suspend fun generateRealTimeOneTimePassword(
        otpContainer: OtpContainer,
        counterContainer: CounterContainer,
    ): Map<String, RealTimeOtpDomain?> {
        return otpContainer.mapValues { (id, otpResult) ->
            counterContainer[id]?.counter?.let {
                val seconds = Clock.System.now().toEpochMilliseconds() / 1000
                val diff = seconds % otpResult.duration.value
                val progress = 1f - (diff / otpResult.duration.value.toFloat())
                val countdown = otpResult.duration.value - diff

                RealTimeOtpDomain(
                    code = manager.generate(
                        timestamp = seconds,
                        secret = otpResult.secret,
                        configuration = OtpConfiguration(
                            period = otpResult.duration.asManager(),
                            digits = otpResult.digit.asManager(),
                            algorithmType = otpResult.type.asManager(),
                        ),
                    ),
                    countdown = countdown.toInt(),
                    progress = progress,
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
