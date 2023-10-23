package io.spherelabs.newtokenpresentation

import io.spherelabs.newtokendomain.model.NewTokenDigit
import io.spherelabs.newtokendomain.model.NewTokenDomain
import io.spherelabs.newtokendomain.model.NewTokenDuration
import io.spherelabs.newtokendomain.model.NewTokenType

sealed interface NewTokenWish {
    data class OnIssuerNameChanged(val issuer: String) : NewTokenWish

    data class OnServiceNameChanged(val serviceName: String) : NewTokenWish
    data class OnInfoChanged(val info: String) : NewTokenWish
    data class OnSecretChanged(val secret: String) : NewTokenWish
    data class OnDigitChanged(val digit: NewTokenDigit) : NewTokenWish
    data class OnTypeChanged(val type: NewTokenType) : NewTokenWish
    data class OnDurationChanged(val duration: NewTokenDuration) : NewTokenWish
    object ToggleSecretVisibility : NewTokenWish
    object OnIssuerFailed : NewTokenWish
    object OnServiceFailed : NewTokenWish
    object OnInfoFailed : NewTokenWish
    object OnDigitFailed : NewTokenWish
    object OnTypeFailed : NewTokenWish
    object OnDurationFailed : NewTokenWish
    data class InsertNewToken(val token: NewTokenDomain) : NewTokenWish
    object OnSubmitClicked : NewTokenWish
    data class Info(val message: String) : NewTokenWish
    data class Failure(val message: String) : NewTokenWish
}
