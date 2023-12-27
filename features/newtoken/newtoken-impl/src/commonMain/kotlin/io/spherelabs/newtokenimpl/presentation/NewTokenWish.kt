package io.spherelabs.newtokenimpl.presentation

import io.spherelabs.newtokenapi.model.NewTokenDomain

sealed interface NewTokenWish {
  data class OnIssuerNameChanged(val issuer: String) : NewTokenWish

  data class OnServiceNameChanged(val serviceName: String) : NewTokenWish

  data class OnInfoChanged(val info: String) : NewTokenWish

  data class OnSecretChanged(val secret: String) : NewTokenWish

  data class OnDigitChanged(val digit: String) : NewTokenWish

  data class OnTypeChanged(val type: String) : NewTokenWish

  data class OnDurationChanged(val duration: Int) : NewTokenWish

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

  data class OnTypeExpandChanged(val isExpanded: Boolean) : NewTokenWish

  data class OnDigitExpandChanged(val isExpanded: Boolean) : NewTokenWish

  data class OnDurationExpandChanged(val isExpanded: Boolean) : NewTokenWish
}
