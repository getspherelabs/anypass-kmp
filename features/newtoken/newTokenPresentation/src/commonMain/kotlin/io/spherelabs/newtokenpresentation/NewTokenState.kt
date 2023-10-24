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
    val duration: Int = 0,
    val type: String = "",
    val digit: String = "",
    val digitFailed: Boolean = false,
    val issuerFailed: Boolean = false,
    val infoFailed: Boolean = false,
    val serviceNameFailed: Boolean = false,
    val secretFailed: Boolean = false,
    val countFailed: Boolean = false,
    val durationFailed: Boolean = false,
    val typeFailed: Boolean = false,
    val secretVisibility: Boolean = false,
    val isTypeExpanded: Boolean = false,
    val isDurationExpanded: Boolean = false,
    val isDigitExpanded: Boolean = false,
) {
    companion object {
        fun types(): List<String> {
            return NewTokenType.values().map {
                it.name
            }
        }

        fun digits(): List<Int> {
            return NewTokenDigit.values().map { newDigit ->
                when(newDigit) {
                    NewTokenDigit.SIX -> 6
                    NewTokenDigit.EIGHT -> 8
                }
            }
        }

        fun duration(): List<Int> {
            return NewTokenDuration.values().map { newDuration ->
                when(newDuration) {
                    NewTokenDuration.FIFTEEN -> 15
                    NewTokenDuration.THIRTY -> 30
                    NewTokenDuration.SIXTY -> 60
                }
            }
        }


        val Empty = NewTokenState()
    }
}
