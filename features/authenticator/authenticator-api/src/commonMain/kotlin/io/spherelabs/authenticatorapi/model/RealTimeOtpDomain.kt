package io.spherelabs.authenticatorapi.model

data class RealTimeOtpDomain(
    val progress: Float,
    val code: String,
    val countdown: Int,
)
