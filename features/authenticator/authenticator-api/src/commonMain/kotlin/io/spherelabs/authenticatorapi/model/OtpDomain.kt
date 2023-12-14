package io.spherelabs.authenticatorapi.model


data class OtpDomain(
    val id: String,
    val secret: String,
    val type: AlgorithmTypeDomain,
    val issuer: String?,
    val info: String?,
    val duration: OtpDurationDomain,
    val digit: OtpDigitDomain,
    val createdTimestamp: Long,
    val serviceName: String?,
)
