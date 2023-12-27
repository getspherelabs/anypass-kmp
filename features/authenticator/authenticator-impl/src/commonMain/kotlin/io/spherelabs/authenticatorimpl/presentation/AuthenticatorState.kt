package io.spherelabs.authenticatorimpl.presentation

import androidx.compose.runtime.Immutable
import io.spherelabs.authenticatorapi.model.OtpDomain
import io.spherelabs.authenticatorapi.model.RealTimeOtpDomain

@Immutable
data class AuthenticatorState(
    val counter: Int = 0,
    val code: Int = 0,
    val issuer: String = "",
    val id: String = "",
    val accounts: List<OtpDomain> = emptyList(),
    val realTimeOtp: Map<String, RealTimeOtpDomain?> = emptyMap(),
    val isRunning: Boolean = true,
) {
  companion object {
    val Empty = AuthenticatorState()
  }
}
