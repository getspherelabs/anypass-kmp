package io.spherelabs.generatepasswordimpl.presentation

import io.spherelabs.generatepasswordimpl.presentation.GeneratePasswordState.Companion.TOTAL_LENGTH_OF_PASSWORD
import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class GeneratePasswordReducer :
    Reducer<GeneratePasswordState, GeneratePasswordWish, GeneratePasswordEffect> {

    override fun reduce(
        currentState: GeneratePasswordState,
        currentWish: GeneratePasswordWish,
    ): Change<GeneratePasswordState, GeneratePasswordEffect> {
        return when (currentWish) {
            is GeneratePasswordWish.GeneratePasswordFailed -> {
                effect { GeneratePasswordEffect.Failure(currentWish.message) }
            }
            GeneratePasswordWish.RouteToBack -> {
                route { GeneratePasswordEffect.RouteToBack }
            }

            is GeneratePasswordWish.OnPasswordChanged -> {
                currentState.passwordChanged(currentWish.password)
            }

            is GeneratePasswordWish.OnUppercaseLengthChanged -> {
                currentState.uppercaseChanged(currentWish.uppercaseValue.toInt())
            }

            is GeneratePasswordWish.OnDigitLengthChanged -> {
                currentState.digitChanged(currentWish.digitValue.toInt())
            }

            is GeneratePasswordWish.OnSpecialLengthChanged -> {
                currentState.specialChanged(currentWish.specialValue.toInt())
            }

            else -> unexpected { currentState }
        }
    }
}

private fun GeneratePasswordState.digitChanged(digitValue: Int): Change<GeneratePasswordState, GeneratePasswordEffect> {
    var totalLength = TOTAL_LENGTH_OF_PASSWORD
    totalLength += digitValue + this.uppercaseLength.toInt() + this.specialLength.toInt()

    return Change(
        state = this.copy(
            digitLength = digitValue.toFloat(),
            length = totalLength,
        ),
    )
}

private fun GeneratePasswordState.uppercaseChanged(uppercaseValue: Int): Change<GeneratePasswordState, GeneratePasswordEffect> {
    var totalLength = TOTAL_LENGTH_OF_PASSWORD
    totalLength += uppercaseValue + this.digitLength.toInt() + this.specialLength.toInt()

    return Change(
        state = this.copy(
            uppercaseLength = uppercaseValue.toFloat(),
            length = totalLength,
        ),
    )
}

private fun GeneratePasswordState.specialChanged(specialValue: Int): Change<GeneratePasswordState, GeneratePasswordEffect> {
    var totalLength = TOTAL_LENGTH_OF_PASSWORD

    totalLength += specialValue + this.uppercaseLength.toInt() + this.digitLength.toInt()

    return Change(
        state = this.copy(
            specialLength = specialValue.toFloat(),
            length = totalLength,
        ),
    )
}

private fun GeneratePasswordState.passwordChanged(newPassword: String): Change<GeneratePasswordState, GeneratePasswordEffect> {
    return Change(
        state = this.copy(password = newPassword),
    )
}

