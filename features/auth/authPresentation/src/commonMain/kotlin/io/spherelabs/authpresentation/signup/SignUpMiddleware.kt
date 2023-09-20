package io.spherelabs.authpresentation.signup

import io.spherelabs.authdomain.CreateEmailAndPassword
import io.spherelabs.meteor.middleware.Middleware

class SignUpMiddleware(
    private val createEmailAndPassword: CreateEmailAndPassword
) : Middleware<SignUpState, SignUpWish> {

    override suspend fun process(
        state: SignUpState,
        wish: SignUpWish,
        next: suspend (SignUpWish) -> Unit
    ) {
        when (wish) {
            SignUpWish.SignUp -> {
                val result = createEmailAndPassword.execute(state.email, state.password, state.name)

                result.onSuccess {
                    next.invoke(SignUpWish.SignUpSuccess)
                }.onFailure { newException ->
                    val failureMsg = newException.message ?: "Error is occurred!"
                    next.invoke(SignUpWish.SignUpFailure(failureMsg))
                }
            }

            else -> {}
        }
    }
}
