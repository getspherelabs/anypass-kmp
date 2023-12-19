package passwordhealthimpl.presentation

sealed interface PasswordHealthEffect {
    data class Failure(val message: String) : PasswordHealthEffect
}
