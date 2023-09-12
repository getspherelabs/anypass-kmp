package io.spherelabs.manager.password

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UppercaseRandomTest {

    private lateinit var uppercaseRandom: UppercaseRandom

    @BeforeTest
    fun setup() {
        uppercaseRandom = UppercaseRandom()
    }

    @Test
    fun `check the digital random is working correctly with length 10`() {
        val random = uppercaseRandom.generate(10)

        println(random)

        assertEquals(10, random.length)
        assertNotNull(random)
        assertEquals(true, random.all { it.isUpperCase() })
    }

    @Test
    fun `check the digital random is working correctly with length 5`() {
        val random = uppercaseRandom.generate(5)

        println(random)

        assertEquals(5, random.length)
        assertNotNull(random)
        assertEquals(true, random.all { it.isUpperCase() })
    }

}