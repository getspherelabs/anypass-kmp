package io.spherelabs.authimpl.presentation.signin


import io.spherelabs.meteor.middleware.Middleware
import io.spherelabs.validation.EmailValidation
import io.spherelabs.validation.PasswordValidation

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
                val isEmailNotValid = !validateEmail.execute(state.email)
                val isPasswordNotValid =
                    !validatePassword.execute(state.password)

                if (isEmailNotValid) {
                    next.invoke(SignInWish.OnEmailFailed)
                }
                if (isPasswordNotValid) {
                    next.invoke(SignInWish.OnPasswordFailed)
                }

                if (!isEmailNotValid && !isPasswordNotValid) {
                    next.invoke(SignInWish.SignIn)
                }
            }

            else -> {}
        }
    }
}
