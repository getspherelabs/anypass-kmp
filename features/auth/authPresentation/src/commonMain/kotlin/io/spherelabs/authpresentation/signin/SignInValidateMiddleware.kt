package io.spherelabs.authpresentation.signin

import io.spherelabs.authdomain.EmailValidation
import io.spherelabs.authdomain.PasswordValidation
import io.spherelabs.meteor.middleware.Middleware

class SignInValidateMiddleware(
    private val validatePassword: PasswordValidation,
    private val validateEmail: EmailValidation
) : Middleware<SignInState, SignInWish> {

    override suspend fun process(state: SignInState, wish: SignInWish, next: suspend (SignInWish) -> Unit) {
        when (wish) {
            SignInWish.OnSignInClick -> {
                if (state.email.isNotEmpty() && !validateEmail.execute(state.email)) {
                    next.invoke(SignInWish.OnEmailFailed)
                }
                if (state.password.isNotEmpty() && !validatePassword.execute(state.password)) {
                    next.invoke(SignInWish.OnPasswordFailed)
                }

                if (state.password.isNotEmpty() &&
                    state.email.isNotEmpty()
                ) {
                    if (!state.emailFailed && !state.passwordFailed) {
                        next.invoke(SignInWish.SignIn)
                    }
                }
            }
            else -> {}
        }
    }
}
