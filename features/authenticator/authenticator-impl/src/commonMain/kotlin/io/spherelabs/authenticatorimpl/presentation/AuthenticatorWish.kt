package io.spherelabs.authenticatorimpl.presentation

sealed interface AuthenticatorWish {
    object GetStartedRealTimeOtp: AuthenticatorWish

}
