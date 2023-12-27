package io.spherelabs.data.local.extension

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isSameAs
import io.spherelabs.data.local.faker.Faker
import io.spherelabs.local.db.PasswordEntity
import kotlin.test.Test

class ReusedPasswordTest {

    @Test
    fun `check the size of reused passwords `() {
        // Given
        val passwords = Faker.password

        // When
        val sizeOfReusedPasswords = passwords.countReusedPassword()

        // Then
        assertThat(2).isSameAs(sizeOfReusedPasswords)
    }

    @Test
    fun `check the returns reused passwords`() {
        // Given
        val passwords = Faker.password

        // When
        val reusedPasswords = passwords.reusedPasswords()

        val expectedPasswords = listOf(
            PasswordEntity(
                id = "1",
                category_id = "1",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Behance",
                notes = "test_notes",
                password = "12345",
                username = "Test1",
                websiteAddress = "Behance.com",
            ),
            PasswordEntity(
                id = "2",
                category_id = "2",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Linkedin",
                notes = "test_notes",
                password = "Strong@2023",
                username = "Test2",
                websiteAddress = "Linkedin.com",
            ),
            PasswordEntity(
                id = "3",
                category_id = "1",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Quora",
                notes = "test_notes",
                password = "12345",
                username = "Test3",
                websiteAddress = "Quora.com",
            ),
            PasswordEntity(
                id = "5",
                category_id = "1",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Behance",
                notes = "test_notes",
                password = "Strong@2023",
                username = "Test1",
                websiteAddress = "Behance.com",
            ),
        )

        assertThat(
            reusedPasswords,
        ).isEqualTo(expectedPasswords)

        assertThat(
            reusedPasswords.size,
        ).isEqualTo(expectedPasswords.size)
    }


}
