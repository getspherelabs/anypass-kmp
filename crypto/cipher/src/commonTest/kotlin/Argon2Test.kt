import io.spherelabs.crypto.cipher.Argon2Engine
import kotlin.test.Test
import kotlin.test.assertEquals

class Argon2Test {

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `GIVEN the argon2 WHEN generate hash value THEN equals same value`() {
        val password = "1dc787126c3261d3891e016a7d6022f60589a982b006ee5006da7eb35b1261ac"
        val randomOutput = ByteArray(32)

        val salt = "8c4402516655e458b6935cf609292512".encodeToByteArray()
        val secret = "b89290567f720edbc3bbbf1171e435ca".encodeToByteArray()
        val additional = "7958dbdb5c5f47bb29e1a585e7ae4c96".encodeToByteArray()

        Argon2Engine(
            type = Argon2Engine.Type.Argon2Id,
            version = Argon2Engine.Version.Ver13,
            salt = salt,
            secret = secret,
            additional = additional,
            iterations = 3,
            parallelism = 4,
            memory = 32,
        ).encrypt(password.encodeToByteArray(), randomOutput)

        val expected = "346a6a7563f3d03b0a95548e93cdafe0f61d7d7ae55f4ebc58b0c404cec220f2"

        assertEquals(expected, randomOutput.toHexString())
    }
}
