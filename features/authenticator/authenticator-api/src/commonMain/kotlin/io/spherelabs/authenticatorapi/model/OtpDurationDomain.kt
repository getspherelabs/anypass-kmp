package io.spherelabs.authenticatorapi.model

enum class OtpDurationDomain(
    val value: Long,
) {
    FIFTEEN(15),
    THIRTY(30),
    SIXTY(60);
}
