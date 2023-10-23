package io.spherelabs.newtokenpresentation

import io.spherelabs.common.uuid4
import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.newtokendomain.model.NewTokenDomain
import io.spherelabs.newtokendomain.usecase.InsertOtpWithCountUseCase
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
                data = NewTokenDomain(
                    id = uuid4(),
                    digit = state.digit,
                    issuer = state.issuer,
                    info = state.info,
                    serviceName = state.serviceName,
                    duration = state.duration,
                    type = state.type,
                    secret = state.secret,
                    createdTimestamp = Clock.System.now().toEpochMilliseconds(),
                ),
                count = newCount,
            )
        }.onSuccess {
            next.invoke(NewTokenWish.Info("Successfully added!"))
        }.onFailure {
            val failureMessage = it.message ?: "Error is occurred."
            next.invoke(NewTokenWish.Failure(failureMessage))
        }
    }
}
