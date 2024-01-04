package io.spherelabs.authimpl.presentation.signin

import io.spherelabs.authapi.domain.usecase.*
import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.passphraseapi.domain.usecase.SetKeyPasswordUseCase

class SignInMiddleware(
    private val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    private val hasCurrentUserExist: HasCurrentUserExist,
    private val setKeyPasswordUseCase: SetKeyPasswordUseCase,
    private val insertUserUseCase: InsertUserUseCase,
) : Middleware<SignInState, SignInWish> {

    override suspend fun process(
        state: SignInState,
        wish: SignInWish,
        next: suspend (SignInWish) -> Unit,
    ) {
        when (wish) {
            SignInWish.SignIn -> {
                val result = signInWithEmailAndPassword.execute(state.email, state.password)

                result
                    .onSuccess {
                        if (state.email == DEFAULT_EMAIL_GOOGLE_REVIEW) {
                            next.invoke(SignInWish.GooglePlayReview)
                        } else {
                            next.invoke(SignInWish.SignInSuccess)
                        }
                    }
                    .onFailure {
                        val failureMessage = it.message ?: "Error is occurred."
                        next.invoke(SignInWish.SignInFailure(failureMessage))
                    }
            }
            is SignInWish.CheckCurrentUser -> {
                runCatching { hasCurrentUserExist.execute() }
                    .onSuccess { isExist ->
                        if (isExist) {
                            next.invoke(SignInWish.SignInSuccess)
                        }
                    }
                    .onFailure {
                        val failureMessage = it.message ?: "Error is occurred."
                        next.invoke(SignInWish.SignInFailure(failureMessage))
                    }
            }
            SignInWish.GooglePlayReview -> {
                runCatching {
                    insertUserUseCase.execute(
                        name = "Google Reviewer",
                        email = state.email,
                        password = state.password
                    )
                    setKeyPasswordUseCase.execute("1234")
                }.onSuccess {
                    next.invoke(SignInWish.SignInSuccess)
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(SignInWish.SignInFailure(failureMessage))
                }
            }
            else -> {}
        }

    }

    companion object {
        private const val DEFAULT_EMAIL_GOOGLE_REVIEW = "test12345@gmail.com"
    }
}
