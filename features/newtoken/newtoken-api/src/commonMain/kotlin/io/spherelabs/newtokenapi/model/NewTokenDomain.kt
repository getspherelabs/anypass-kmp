package io.spherelabs.newtokenapi.model

data class NewTokenDomain(
    val id: String,
    val secret: String,
    val type: NewTokenType,
    val issuer: String?,
    val info: String?,
    val duration: NewTokenDuration,
    val digit: NewTokenDigit,
    val createdTimestamp: Long,
    val serviceName: String?,
)
