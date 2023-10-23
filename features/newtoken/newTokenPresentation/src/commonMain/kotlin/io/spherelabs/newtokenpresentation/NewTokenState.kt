package io.spherelabs.newtokenpresentation

import androidx.compose.runtime.Immutable
import io.spherelabs.newtokendomain.model.NewTokenDigit
import io.spherelabs.newtokendomain.model.NewTokenDuration
import io.spherelabs.newtokendomain.model.NewTokenType

@Immutable
data class NewTokenState(
    val issuer: String = "",
    val info: String = "",
    val serviceName: String = "",
    val secret: String = "",
    val count: Long = 0L,
    val duration: NewTokenDuration = NewTokenDuration.FIFTEEN,
    val type: NewTokenType = NewTokenType.SHA1,
    val digit: NewTokenDigit = NewTokenDigit.EIGHT,
    val digitFailed: Boolean = false,
    val issuerFailed: Boolean = false,
    val infoFailed: Boolean = false,
    val serviceNameFailed: Boolean = false,
    val secretFailed: Boolean = false,
    val countFailed: Boolean = false,
    val durationFailed: Boolean = false,
    val typeFailed: Boolean = false,
) {
    companion object {
        val Empty = NewTokenState()
    }
}
