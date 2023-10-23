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
    val duration: NewTokenDuration = NewTokenDuration.FIFTEEN,
    val type: NewTokenType = NewTokenType.SHA1,
    val digit: NewTokenDigit = NewTokenDigit.EIGHT,
    val createdTimestamp: Long = 0L,
) {
    companion object {
        val Empty = NewTokenState()
    }
}
