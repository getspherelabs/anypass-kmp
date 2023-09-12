package io.spherelabs.generatepassworddomain

import io.spherelabs.manager.password.PasswordManager

interface GeneratePassword {
    suspend fun generate(
        uppercaseLength: Int = 0,
        digitLength: Int = 0,
        lowercaseLength: Int = 0,
        specialLength: Int = 0,
        length: Int = 10
    ): Result<String>
}

class DefaultGeneratePassword(
    private val manager: PasswordManager
) : GeneratePassword {

    override suspend fun generate(
        uppercaseLength: Int,
        digitLength: Int,
        lowercaseLength: Int,
        specialLength: Int,
        length: Int
    ): Result<String> {
        val result =
            manager.generate(
                uppercaseLength,
                digitLength,
                lowercaseLength,
                specialLength,
                length
            )


        return when {
            result.length < 10 -> Result.failure(Exception("The password length is less than 10."))
            result.isEmpty() -> Result.failure(Exception("The password is empty."))
            else -> Result.success(result)
        }

    }
}