package io.spherelabs.authpresentation.signin

import io.spherelabs.authdomain.HasCurrentUserExist
import io.spherelabs.authdomain.NameValidation
import io.spherelabs.authdomain.PasswordValidation
import io.spherelabs.authdomain.SignInWithEmailAndPassword
import io.spherelabs.meteor.middleware.Middleware

class SignInMiddleware(
    private val signInWithEmailAndPassword: SignInWithEmailAndPassword,
    private val hasCurrentUserExist: HasCurrentUserExist
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
                    .onSuccess { next.invoke(SignInWish.SignInSuccess) }
                    .onFailure {
                        val failureMessage = it.message ?: "Error is occurred."
                        next.invoke(SignInWish.SignInFailure(failureMessage))
                    }
            }

            is SignInWish.CheckCurrentUser -> {
                runCatching { hasCurrentUserExist.execute() }
                    .onSuccess { next.invoke(SignInWish.HasCurrentUser(it)) }
                    .onFailure {
                        val failureMessage = it.message ?: "Error is occurred."
                        next.invoke(SignInWish.SignInFailure(failureMessage))
                    }
            }

            else -> {}
        }
    }
}
