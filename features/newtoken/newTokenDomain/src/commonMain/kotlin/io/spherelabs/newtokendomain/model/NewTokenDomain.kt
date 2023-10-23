package io.spherelabs.newtokendomain.model

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


enum class NewTokenType {
    SHA1,
    SHA256,
    SHA512;
}


enum class NewTokenDuration(
    val value: Long,
) {
    FIFTEEN(15),
    THIRTY(30),
    SIXTY(60);
}

enum class NewTokenDigit(val number: Long) {
    SIX(6),
    EIGHT(8);
}
