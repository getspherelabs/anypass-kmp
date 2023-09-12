package io.spherelabs.generatepasswordpresentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class GeneratePasswordReducer :
    Reducer<GeneratePasswordState, GeneratePasswordWish, GeneratePasswordEffect> {

    override fun reduce(
        currentState: GeneratePasswordState,
        currentWish: GeneratePasswordWish
    ): Change<GeneratePasswordState, GeneratePasswordEffect> {
        return when (currentWish) {
            is GeneratePasswordWish.GeneratePasswordFailed -> {
                effect { GeneratePasswordEffect.Failure(currentWish.message) }
            }
            is GeneratePasswordWish.OnPasswordChanged -> {
                expect {
                    currentState.copy(
                        password = currentWish.password
                    )
                }
            }
            is GeneratePasswordWish.OnUppercaseLengthChanged -> {
                val difference = currentWish.value.toInt()- currentState.length
                val newLength = currentState.length + difference + currentState.digitLength.toInt()

                expect {
                    currentState.copy(
                        uppercaseLength = currentWish.value,
                        length =  newLength + 10
                    )
                }
            }
            is GeneratePasswordWish.OnDigitLengthChanged -> {
                val difference = currentWish.value.toInt()- currentState.length

                val newLength = currentState.length  + difference + currentState.digitLength.toInt()

                expect {
                    currentState.copy(
                        digitLength = currentWish.value,
                        length =  newLength + 10
                    )
                }
            }
            else -> unexpected { currentState }
        }
    }
}