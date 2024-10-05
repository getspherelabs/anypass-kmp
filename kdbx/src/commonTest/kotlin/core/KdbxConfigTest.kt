package core

import io.spherelabs.crypto.tinypass.database.core.KdbxConfiguration
import kotlin.test.Test
import kotlin.test.assertNotNull

class KdbxConfigTest {

    @Test
    fun `GIVEN the passphrase WHEN uses secure bytes THEN checks it is not null`() {
        val passphrase = "kdbx_config"
        val path = "config.kdbx"

        val config = KdbxConfiguration.of(path = path, passphrase = passphrase)

        assertNotNull(config.passphrase)
    }
}
