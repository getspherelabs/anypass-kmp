package io.spherelabs.manager.password

interface PasswordManager {
    suspend fun generate(
        uppercaseLength: Int = 0,
        digitLength: Int = 0,
        lowercaseLength: Int = 0,
        specialLength: Int = 0,
        length: Int = 10
    ): String
}

class DefaultPasswordManager(
    private val digitRandom: DigitRandom,
    private val lowercaseRandom: LowercaseRandom,
    private val specialRandom: SpecialRandom,
    private val uppercaseRandom: UppercaseRandom
) : PasswordManager {
    override suspend fun generate(
        uppercaseLength: Int,
        digitLength: Int,
        lowercaseLength: Int,
        specialLength: Int,
        length: Int
    ): String {
        var password = ""

        password += digitRandom.generate(digitLength)
        password += uppercaseRandom.generate(uppercaseLength)
        password += lowercaseRandom.generate()
        password += specialRandom.generate(specialLength)

        return password
    }
}