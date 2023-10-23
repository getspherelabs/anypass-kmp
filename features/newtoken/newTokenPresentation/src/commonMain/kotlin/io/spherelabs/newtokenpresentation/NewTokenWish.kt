package io.spherelabs.newtokenpresentation

sealed interface NewTokenWish {
    data class OnIssuerNameChanged(val issuer: String) : NewTokenWish
    data class OnServiceNameChanged(val serviceName: String) : NewTokenWish
    data class OnInfoChanged(val info: String) : NewTokenWish
    data class OnSecretChanged(val secret: String) : NewTokenWish
    data class OnDigitChanged(val digit: String) : NewTokenWish
    data class OnTypeChanged(val type: String) : NewTokenWish

}
