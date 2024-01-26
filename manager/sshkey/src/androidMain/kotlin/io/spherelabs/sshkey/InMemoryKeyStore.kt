package io.spherelabs.sshkey

import android.security.keystore.KeyProperties
import java.io.InputStream
import java.io.OutputStream
import java.security.Key
import java.security.KeyStore
import java.security.KeyStoreSpi
import java.security.Provider
import java.security.SecureRandom
import java.security.Security
import java.security.cert.Certificate
import java.security.spec.AlgorithmParameterSpec
import java.util.Date
import java.util.Enumeration
import javax.crypto.KeyGenerator
import javax.crypto.KeyGeneratorSpi
import javax.crypto.SecretKey

object FakeAndroidKeyStore {

    val setup by lazy {
        Security.addProvider(object : Provider("AndroidKeyStore", 1.0, "") {
            init {
                put("KeyStore.AndroidKeyStore", FakeKeyStore::class.java.name)
                put("eyPairGenerator.RSA", FakeRsaKeyGenerator::class.java.name)
            }
        })
    }

    @Suppress("unused")
    class FakeKeyStore : KeyStoreSpi() {
        private val wrapped = KeyStore.getInstance(KeyStore.getDefaultType())

        override fun engineIsKeyEntry(alias: String?): Boolean = wrapped.isKeyEntry(alias)
        override fun engineIsCertificateEntry(alias: String?): Boolean = wrapped.isCertificateEntry(alias)
        override fun engineGetCertificate(alias: String?): Certificate = wrapped.getCertificate(alias)
        override fun engineGetCreationDate(alias: String?): Date = wrapped.getCreationDate(alias)
        override fun engineDeleteEntry(alias: String?) = wrapped.deleteEntry(alias)
        override fun engineSetKeyEntry(alias: String?, key: Key?, password: CharArray?, chain: Array<out Certificate>?) =
            wrapped.setKeyEntry(alias, key, password, chain)

        override fun engineSetKeyEntry(alias: String?, key: ByteArray?, chain: Array<out Certificate>?) = wrapped.setKeyEntry(alias, key, chain)
        override fun engineStore(stream: OutputStream?, password: CharArray?) = wrapped.store(stream, password)
        override fun engineSize(): Int = wrapped.size()
        override fun engineAliases(): Enumeration<String> = wrapped.aliases()
        override fun engineContainsAlias(alias: String?): Boolean = wrapped.containsAlias(alias)
        override fun engineLoad(stream: InputStream?, password: CharArray?) = wrapped.load(stream, password)
        override fun engineGetCertificateChain(alias: String?): Array<Certificate> = wrapped.getCertificateChain(alias)
        override fun engineSetCertificateEntry(alias: String?, cert: Certificate?) = wrapped.setCertificateEntry(alias, cert)
        override fun engineGetCertificateAlias(cert: Certificate?): String = wrapped.getCertificateAlias(cert)
        override fun engineGetKey(alias: String?, password: CharArray?): Key = wrapped.getKey(alias, password)
    }

    @Suppress("unused")
    class FakeRsaKeyGenerator : KeyGeneratorSpi() {
        private val wrapped = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA)

        override fun engineInit(random: SecureRandom?) = Unit
        override fun engineInit(params: AlgorithmParameterSpec?, random: SecureRandom?) = Unit
        override fun engineInit(keysize: Int, random: SecureRandom?) = Unit
        override fun engineGenerateKey(): SecretKey = wrapped.generateKey()
    }
}
