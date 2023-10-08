package io.spherelabs.manager.password

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest

class PasswordManagerTest {
    private lateinit var passwordManager: PasswordManager

    @BeforeTest
    fun setup() {
        passwordManager = DefaultPasswordManager(
            digitRandom = DigitRandom(),
            uppercaseRandom = UppercaseRandom(),
            lowercaseRandom = LowercaseRandom(),
            specialRandom = SpecialRandom(),
        )
    }

    @Test
    fun `check the password manager is working correctly`() = runTest {
        val random = passwordManager.generate()
        println(random)
        assertEquals(10, random.length)
        assertEquals(true, random.all { it.isLowerCase() })
    }

    @Test
    fun `check the password manager is working correctly with special`() = runTest {
        val random = passwordManager.generate(specialLength = 2, uppercaseLength = 2, digitLength = 2)

        println(random)
        assertEquals(16, random.length)
        assertEquals(false, random.all { it.isLowerCase() })
    }
}
