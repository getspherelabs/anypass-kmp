package io.spherelabs.sshkey

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAKeyGenParameterSpec
import java.security.spec.X509EncodedKeySpec

actual fun ByteArray.toPrivateKey(): PrivateKey {
    val spec = PKCS8EncodedKeySpec(this)
    val kf = KeyFactory.getInstance("RSA")
    return PrivateKey(kf.generatePrivate(spec))
}

actual fun ByteArray.toPublicKey(): PublicKey {
    val generator = KeyFactory.getInstance("RSA")
    val spec = X509EncodedKeySpec(this)
    return PublicKey(
        generator.generatePublic(spec),
    )
}



