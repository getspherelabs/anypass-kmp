package io.spherelabs.data.local

import assertk.assertThat
import assertk.assertions.hasSize
import io.spherelabs.data.local.db.isStrongPassword
import io.spherelabs.data.local.faker.Faker
import kotlin.test.Test

class StrongAndWeakPasswordTest {

    @Test
    fun `check the exist strong password`() {
        val passwords = Faker.password

        val sizeOfStrongPasswords = passwords.filter { it.isStrongPassword() }
        val sizeOfWeakPasswords = passwords.filterNot { it.isStrongPassword() }

        assertThat(sizeOfStrongPasswords).hasSize(1)
        assertThat(sizeOfWeakPasswords).hasSize(2)
    }
}
