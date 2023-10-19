package io.spherelabs.data.local.enum

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isSameAs
import io.spherelabs.data.local.db.adapter.nonSynchronizedLazy
import io.spherelabs.data.local.db.otp.AlgorithmTypeEntity
import kotlin.test.Test

class OtpTypeTest {

    private val currentValue by nonSynchronizedLazy {
        AlgorithmTypeEntity
    }

    @Test
    fun `check the value is correct OTP`() {
        val type = "SHA1"
        val result = currentValue(type)?.name

        assertThat(result).isSameAs(type)
    }

    @Test
    fun `check the otp type is return correct value`() {
        val type = AlgorithmTypeEntity.SHA1
        val result = currentValue("SHA1")

        assertThat(result).isEqualTo(type)
    }
}
