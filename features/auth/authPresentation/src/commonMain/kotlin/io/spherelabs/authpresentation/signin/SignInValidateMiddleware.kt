package io.spherelabs.authpresentation.signin

import io.spherelabs.authdomain.EmailValidation
import io.spherelabs.authdomain.PasswordValidation
import io.spherelabs.meteor.middleware.Middleware

class SignInValidateMiddleware(
    private val validatePassword: PasswordValidation,
    private val validateEmail: EmailValidation,
) : Middleware<SignInState, SignInWish> {

    override suspend fun process(
        state: SignInState,
        wish: SignInWish,
        next: suspend (SignInWish) -> Unit,
    ) {
        when (wish) {
            SignInWish.OnLoginClicked -> {
                val isEmailValid = state.email.isNotEmpty() && validateEmail.execute(state.email)
                val isPasswordValid =
                    state.password.isNotEmpty() && validatePassword.execute(state.password)

                if (!isEmailValid) {
                    next.invoke(SignInWish.OnEmailFailed)
                }
                if (!isPasswordValid) {
                    next.invoke(SignInWish.OnPasswordFailed)
                }

                if (isEmailValid && isPasswordValid) {
                    next.invoke(SignInWish.SignIn)
                }
            }

            else -> {}
        }
    }
}
