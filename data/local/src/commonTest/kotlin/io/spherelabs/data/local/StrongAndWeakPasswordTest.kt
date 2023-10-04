package io.spherelabs.data.local

import assertk.assertThat
import assertk.assertions.hasSize
import io.spherelabs.data.local.db.isStrongPassword
import io.spherelabs.local.db.Password
import kotlin.test.Test

class StrongAndWeakPasswordTest {

    @Test
    fun `check the exist strong password`() {
        val passwords = listOf(
            Password(
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
            Password(
                id = "2",
                category_id = "1",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Linkedin",
                notes = "test_notes",
                password = "Strong@2023",
                username = "Test2",
                websiteAddress = "Linkedin.com",
            ),
            Password(
                id = "2",
                category_id = "1",
                email = "test@gmail.com",
                image = "test_2_image",
                title = "Quora",
                notes = "test_notes",
                password = "AbCdEfG1",
                username = "Test3",
                websiteAddress = "Quora.com",
            ),
        )

        val sizeOfStrongPasswords = passwords.filter { it.isStrongPassword() }
        val sizeOfWeakPasswords = passwords.filterNot { it.isStrongPassword() }

        assertThat(sizeOfStrongPasswords).hasSize(1)
        assertThat(sizeOfWeakPasswords).hasSize(2)
    }
}
