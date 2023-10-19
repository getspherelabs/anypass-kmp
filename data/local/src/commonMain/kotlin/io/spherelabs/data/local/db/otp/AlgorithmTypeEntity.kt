package io.spherelabs.data.local.db.otp

enum class AlgorithmTypeEntity {
    SHA1,
    SHA256,
    SHA512;

    companion object {
        operator fun invoke(name: String): AlgorithmTypeEntity? {
            return fromRaw(name)
        }

        private fun fromRaw(name: String): AlgorithmTypeEntity? {
            return values().find { it.name == name }
        }
    }
}
