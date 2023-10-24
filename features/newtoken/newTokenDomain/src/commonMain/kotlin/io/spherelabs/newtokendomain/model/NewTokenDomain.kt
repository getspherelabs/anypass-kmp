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
) {
}


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

    companion object {
        fun fromInt(value: Int): NewTokenDuration {
            return when (value) {
                15 -> NewTokenDuration.FIFTEEN
                30 -> NewTokenDuration.THIRTY
                60 -> NewTokenDuration.SIXTY
                else -> {
                    NewTokenDuration.FIFTEEN
                }
            }
        }
    }
}


enum class NewTokenDigit(val number: Long) {
    SIX(6),
    EIGHT(8);

    companion object {
        fun fromInt(value: Int): NewTokenDigit {
            return when (value) {
                6 -> NewTokenDigit.SIX
                8 -> NewTokenDigit.EIGHT
                else -> NewTokenDigit.SIX
            }
        }
    }
}
