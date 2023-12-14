package io.spherelabs.data.local.db.otp

import io.spherelabs.authenticatorapi.model.AlgorithmTypeDomain
import io.spherelabs.newtokenapi.model.NewTokenType


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

fun AlgorithmTypeEntity.asDomain(): AlgorithmTypeDomain {
    return when (this) {
        AlgorithmTypeEntity.SHA1 -> AlgorithmTypeDomain.SHA1
        AlgorithmTypeEntity.SHA256 -> AlgorithmTypeDomain.SHA256
        AlgorithmTypeEntity.SHA512 -> AlgorithmTypeDomain.SHA512
    }
}

fun AlgorithmTypeDomain.asEntity(): AlgorithmTypeEntity {
    return when (this) {
        AlgorithmTypeDomain.SHA1 -> AlgorithmTypeEntity.SHA1
        AlgorithmTypeDomain.SHA256 -> AlgorithmTypeEntity.SHA256
        AlgorithmTypeDomain.SHA512 -> AlgorithmTypeEntity.SHA512
    }
}

fun NewTokenType.asEntity(): AlgorithmTypeEntity {
    return when(this) {
        NewTokenType.SHA1 -> AlgorithmTypeEntity.SHA1
        NewTokenType.SHA256 -> AlgorithmTypeEntity.SHA256
        NewTokenType.SHA512 -> AlgorithmTypeEntity.SHA512
    }
}
