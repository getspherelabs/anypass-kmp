package io.spherelabs.sshkey

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyPairGenerator
import java.security.KeyStore

actual class MultiplatformKeyGenerator {
    private val alias = "sshkey"

    actual fun generate(): MultiplatformKeyPair {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        val keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")
        val spec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_SIGN
                or KeyProperties.PURPOSE_VERIFY,
        )
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
            .setDigests(KeyProperties.DIGEST_SHA256)
            .setKeySize(2048)
            .build()
        keyPairGenerator.initialize(spec)
        val javaKeyPair = keyPairGenerator.generateKeyPair()

        return MultiplatformKeyPair(javaKeyPair)

    }
}
