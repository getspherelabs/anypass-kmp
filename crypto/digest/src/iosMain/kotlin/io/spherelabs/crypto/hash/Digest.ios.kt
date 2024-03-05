package io.spherelabs.crypto.hash

actual fun digest(type: Algorithm): Digest {
    return when (type) {
        Algorithm.Sha256 -> Sha256()
        Algorithm.Sha512 -> Sha512()
    }
}
