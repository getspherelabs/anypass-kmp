package io.spherelabs.generatepasswordimpl.presentation

import io.spherelabs.generatepasswordapi.domain.usecase.GeneratePasswordUseCase
import io.spherelabs.generatepasswordapi.domain.usecase.InsertPasswordHistoryUseCase
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.datetime.Clock

class GeneratePasswordMiddleware(
    private val generatePasswordUseCase: GeneratePasswordUseCase,
    private val insertPasswordHistoryUseCase: InsertPasswordHistoryUseCase,
) :
    Middleware<GeneratePasswordState, GeneratePasswordWish> {

    override suspend fun process(
        state: GeneratePasswordState,
        wish: GeneratePasswordWish,
        next: suspend (GeneratePasswordWish) -> Unit,
    ) {
        when (wish) {
            is GeneratePasswordWish.GeneratePassword -> {
                generatePasswordUseCase
                    .execute(
                        uppercaseLength = wish.uppercaseLength,
                        lowercaseLength = wish.lowercaseLength,
                        digitLength = wish.digitLength,
                        length = wish.length,
                        specialLength = wish.specialLength,
                    )
                    .onSuccess { newPassword ->
                        next.invoke(
                            GeneratePasswordWish.AddPasswordHistory(
                                newPassword,
                                createdAt = Clock.System.now().toEpochMilliseconds(),
                            ),
                        )
                        next.invoke(GeneratePasswordWish.OnPasswordChanged(newPassword))
                    }
                    .onFailure {
                        val failureMessage = it.message ?: "Error is occurred."

                        next.invoke(GeneratePasswordWish.GeneratePasswordFailed(failureMessage))
                    }
            }

            is GeneratePasswordWish.AddPasswordHistory -> {
                runCatching {
                    insertPasswordHistoryUseCase.execute(
                        wish.password,
                        wish.createdAt,
                    )
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."

                    next.invoke(GeneratePasswordWish.GeneratePasswordFailed(failureMessage))
                }

            }
            else -> {}
        }
    }
}
