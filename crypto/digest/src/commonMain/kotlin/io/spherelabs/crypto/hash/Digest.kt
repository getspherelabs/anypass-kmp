package io.spherelabs.crypto.hash

/**
 * The [Digest] is used for generating a fixed-size hash value or digest from variable-sized input data.
 *
 * It provides applications the functionality of a message digest algorithm, such as SHA-1 or SHA-256.
 *
 * Message digests are secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value.
 *
 * https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html
 *
 *
 *
 */
interface Digest {
    fun digest(buffer: ByteArray, offset: Int = 0, length: Int = buffer.size)
    fun digest(): ByteArray
}


expect fun digest(type: Algorithm): Digest

fun ByteArray.sha256(): ByteArray {
    val msgDigest = digest(Algorithm.Sha256)
    msgDigest.digest(this)
    return msgDigest.digest()
}


fun ByteArray.sha512(): ByteArray {
    val msgDigest = digest(Algorithm.Sha512)
    msgDigest.digest(this)
    return msgDigest.digest()
}
