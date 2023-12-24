package io.spherelabs.newtokenimpl.presentation

import io.spherelabs.common.uuid4
import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.newtokenapi.domain.usecase.InsertOtpWithCountUseCase
import io.spherelabs.newtokenapi.model.NewTokenDigit
import io.spherelabs.newtokenapi.model.NewTokenDomain
import io.spherelabs.newtokenapi.model.NewTokenDuration
import io.spherelabs.newtokenapi.model.NewTokenType
import kotlinx.datetime.Clock

class NewTokenMiddleware(
    private val insertOtpWithCountUseCase: InsertOtpWithCountUseCase,
) : Middleware<NewTokenState, NewTokenWish> {

  override suspend fun process(
      state: NewTokenState,
      wish: NewTokenWish,
      next: suspend (NewTokenWish) -> Unit,
  ) {
    when (wish) {
      NewTokenWish.OnSubmitClicked -> {
        handleInsertNewToken(state, next)
      }
      else -> {}
    }
  }

  private suspend fun handleInsertNewToken(
      state: NewTokenState,
      next: suspend (NewTokenWish) -> Unit,
  ) {
    runCatching {
          val newCount = state.count

          insertOtpWithCountUseCase.execute(
              data =
                  NewTokenDomain(
                      id = uuid4(),
                      digit = NewTokenDigit.fromInt(state.digit.toInt()),
                      issuer = "Behance",
                      info = state.info,
                      serviceName = state.serviceName,
                      duration = NewTokenDuration.fromInt(state.duration),
                      type = NewTokenType.valueOf(state.type),
                      secret = state.secret,
                      createdTimestamp = Clock.System.now().toEpochMilliseconds(),
                  ),
              count = Clock.System.now().toEpochMilliseconds(),
          )
        }
        .onSuccess { next.invoke(NewTokenWish.Info("Successfully added!")) }
        .onFailure {
          val failureMessage = it.message ?: "Error is occurred."
          next.invoke(NewTokenWish.Failure(failureMessage))
        }
  }

  private fun isValid(state: NewTokenState): Boolean {
    val issuerValid = state.issuer.isNotEmpty()
    val secretValid = state.secret.isNotEmpty()
    val serviceNameValid = state.serviceName.isNotEmpty()

    return issuerValid && secretValid && serviceNameValid
  }
}
