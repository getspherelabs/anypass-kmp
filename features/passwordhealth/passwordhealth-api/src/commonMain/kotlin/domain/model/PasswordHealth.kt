package domain.model

data class PasswordHealth(
    val id: String,
    val name: String,
    val email: String,
    val image: String,
    val type: PasswordType,
)
