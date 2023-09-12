package io.spherelabs.manager.password

import kotlin.test.*

class LowercaseRandomTest {

    private lateinit var lowercaseRandom: LowercaseRandom

    @BeforeTest
    fun setup() {
        lowercaseRandom = LowercaseRandom()
    }

    @Test
    fun `check the digital random is working correctly with length 10`() {
        val random = lowercaseRandom.generate()

        println(random)

        assertEquals(10, random.length)
        assertNotNull(random)
        assertEquals(true, random.all { it.isLowerCase() })
    }

}