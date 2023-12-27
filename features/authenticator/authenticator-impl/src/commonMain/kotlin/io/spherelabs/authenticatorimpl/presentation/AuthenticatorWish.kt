package io.spherelabs.authenticatorimpl.presentation

import io.spherelabs.authenticatorapi.model.OtpDomain
import io.spherelabs.authenticatorapi.model.RealTimeOtpDomain

sealed interface AuthenticatorWish {
  object GetStartedRealTimeOtp : AuthenticatorWish

  data class Failure(val message: String) : AuthenticatorWish

  data class GetOneTimePassword(val otp: Map<String, RealTimeOtpDomain?>) : AuthenticatorWish

  object GetStartedAccounts : AuthenticatorWish

  data class GetAccounts(val accounts: List<OtpDomain>) : AuthenticatorWish

  data class OnCountdownChanged(val countdown: Long) : AuthenticatorWish

  data class OnRunningChanged(val isRunning: Boolean) : AuthenticatorWish

  object OnCancellationOtp : AuthenticatorWish
}
