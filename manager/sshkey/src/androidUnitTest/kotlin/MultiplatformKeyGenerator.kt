import io.spherelabs.sshkey.FakeAndroidKeyStore
import io.spherelabs.sshkey.KeyGenerator
import io.spherelabs.sshkey.printPublicKey
import java.security.KeyStore
import kotlin.test.BeforeTest
import org.junit.Test

class MultiplatformKeyGeneratorTest {

    @BeforeTest
    fun setup() {
        FakeAndroidKeyStore.setup
        KeyStore.getInstance("AndroidKeyStore")
    }

    @Test
    fun `GIVEN the key generator WHEN generate THEN equals existing`() {
        val keyGenerator = KeyGenerator.instance

        val result = keyGenerator.generate()

        val publicKey = printPublicKey(result)

        println(publicKey)
    }
}
