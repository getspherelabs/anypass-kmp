package io.spherelabs.data.local.extension

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.spherelabs.data.local.faker.Faker
import kotlin.test.Test

class CalculationPasswordHealthTest {

    @Test
    fun `GIVEN passwords WHEN calculates the password health THEN equals over half`() {
        // Given
        val passwords = Faker.password

        // When
        val result = passwords.calculatePasswordHealth()

        // Then
        assertThat(result).isEqualTo(52.0)
    }
}
