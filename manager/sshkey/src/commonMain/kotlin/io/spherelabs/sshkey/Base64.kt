package io.spherelabs.sshkey

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun printPublicKey(keyPairMap: MultiplatformKeyPair): String {
    return Base64.encode(
        keyPairMap.publicKey.encoded, 0,
    )
}

@OptIn(ExperimentalEncodingApi::class)
fun ByteArray.toHex(): String {
    return Base64.encode(
        this, 0,
    )
}

@OptIn(ExperimentalEncodingApi::class)
fun printPrivateKey(keyPairMap: MultiplatformKeyPair): String {
    return Base64.encode(
        keyPairMap.privateKey.encoded, 0,
    )
}
