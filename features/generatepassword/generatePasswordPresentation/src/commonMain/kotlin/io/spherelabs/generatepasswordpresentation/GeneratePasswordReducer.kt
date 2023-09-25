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
        expect { currentState.copy(password = currentWish.password) }
      }
      is GeneratePasswordWish.OnUppercaseLengthChanged -> {
        var totalLength = 10
        totalLength += currentWish.value.toInt() + currentState.digitLength.toInt()

        expect { currentState.copy(uppercaseLength = currentWish.value, length = totalLength) }
      }
      is GeneratePasswordWish.OnDigitLengthChanged -> {
        var totalLength = 10
        totalLength += currentWish.value.toInt() + currentState.uppercaseLength.toInt()

        expect { currentState.copy(digitLength = currentWish.value, length = totalLength) }
      }
      else -> unexpected { currentState }
    }
  }
}
