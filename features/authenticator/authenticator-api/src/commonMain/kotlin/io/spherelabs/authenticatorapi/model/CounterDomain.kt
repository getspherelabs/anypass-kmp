package io.spherelabs.authenticatorapi.model

data class CounterDomain(
    val otpId: String,
    val counter: Long,
)
