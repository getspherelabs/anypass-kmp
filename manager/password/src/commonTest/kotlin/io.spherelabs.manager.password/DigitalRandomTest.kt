package io.spherelabs.manager.password

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DigitalRandomTest {

    private lateinit var digitRandom: DigitRandom

    @BeforeTest
    fun setup() {
        digitRandom = DigitRandom()
    }

    @Test
    fun `check the digital random is working correctly with length 10`() {
        val random = digitRandom.generate(10)

        println(random)

        assertEquals(10, random.length)
        assertNotNull(random)
        assertEquals(true, random.all { it.isDigit() })
        assertEquals(false, random.all { it.isLowerCase() })
    }

    @Test
    fun `check the digital random is working correctly with length 5`() {
        val random = digitRandom.generate(5)

        println(random)

        assertEquals(5, random.length)
        assertNotNull(random)
        assertEquals(true, random.all { it.isDigit() })
        assertEquals(false, random.all { it.isLowerCase() })
    }

    @Test
    fun `check the digital random is working correctly with length 0`() {
        val random = digitRandom.generate(5)

        println(random)

        assertEquals(5, random.length)
        assertNotNull(random)
        assertEquals(true, random.all { it.isDigit() })
        assertEquals(false, random.all { it.isLowerCase() })
    }
}