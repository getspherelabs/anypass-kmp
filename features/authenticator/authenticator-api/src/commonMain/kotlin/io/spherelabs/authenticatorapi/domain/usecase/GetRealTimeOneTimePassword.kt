package io.spherelabs.authenticatorapi.domain.usecase

import io.spherelabs.authenticatorapi.model.RealTimeOtpDomain

interface GetRealTimeOneTimePassword {
  fun execute(onResult: suspend (Map<String, RealTimeOtpDomain?>) -> Unit)

  fun cancel()
}
