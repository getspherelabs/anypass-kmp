package domain.model

data class PasswordStats(
    val totalPasswords: Int,
    val sizeOfWeakPasswords: Int,
    val sizeOfReusedPasswords: Int,
    val sizeOfStrongPasswords: Int,
)
