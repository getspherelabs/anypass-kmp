import io.spherelabs.sshkey.toHex
import io.spherelabs.sshkey.toPem
import io.spherelabs.sshkey.toPublicKey
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@OptIn(ExperimentalEncodingApi::class)
class MultiplatformKeyPairTest {

    @Test
    fun `GIVEN hex key pair WHEN encoding THEN checks the encoded key`() {
        val hexKey = """
            MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo5W5pdnDNIR+6aIYztwCF3TA0jIqSe3l/GHfdUGoCwy4SnVh3JGoWlDPP61QyrYU8exwNfYlKVKpL4TSoUuazGTkk9Y1KPDtIdI72UAXM6FnhNR5PW0vBzxESq1MXfMIvOcZx5LckrSXiOehthXhhvFPR1Sgp2CORST9V1aingd+aT/u1ewFqcCIReFowQ9KWZlc2+1Fmt/DwaSLASNlUnGAPF23Wq1mVK704tFsizPlA3U2fzdCa9qpP8pcTLsNC/epLKzFhHQWbD2LvgFCMfuPUX9tp5nYe0puZQSiqmY7SdDz9jxBRIdlZOuvWQICiFIDmry0I15SLcV0OeccrQIDAQAB
        """.trimIndent()

        val base64key = hexKey.toPem()

        val publicKey = Base64.decode(base64key).toPublicKey()

        assertNotNull(publicKey.encoded)
        assertEquals(base64key, publicKey.encoded.toHex())
    }
}
