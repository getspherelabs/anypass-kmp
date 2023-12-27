package io.spherelabs.data.local.repository

import domain.model.PasswordHealth
import domain.model.PasswordType
import domain.repository.PasswordHealthRepository
import io.spherelabs.data.local.db.PasswordDao
import io.spherelabs.data.local.extension.calculatePasswordHealth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultPasswordHealthRepository(
    private val passwordDao: PasswordDao,
) : PasswordHealthRepository {

    override fun getSizeOfStrongPasswords(): Flow<Int> {
        return passwordDao.getSizeOfStrongPasswords()
    }

    override fun getSizeOfWeakPasswords(): Flow<Int> {
        return passwordDao.getSizeOfWeakPasswords()
    }

    override fun getTotalPasswords(): Flow<Int> {
        return passwordDao.getTotalPasswords()
    }

    override fun getSizeOfReusedPasswords(): Flow<Int> {
        return passwordDao.getSizeOfReusedPasswords()
    }

    override fun getCurrentPasswordHealth(): Flow<Double> {
        return passwordDao.getAllPassword().map {
            it.calculatePasswordHealth()
        }
    }

    override fun getWeakPasswords(): Flow<List<PasswordHealth>> {
        return passwordDao.getWeakPasswords().map { currentPasswords ->
            currentPasswords.map { entity ->
                PasswordHealth(
                    id = entity.id,
                    name = requireNotNull(entity.title) {
                        "Title is null with ID: ${entity.id}."
                    },
                    email = requireNotNull(entity.email) {
                        "Email is null with ID: ${entity.email}."
                    },
                    image = requireNotNull(entity.image) {
                        "Image is null with ID: ${entity.id}."
                    },
                    type = PasswordType.Weak,
                )
            }
        }
    }

    override fun getStrongPasswords(): Flow<List<PasswordHealth>> {
        return passwordDao.getStrongPasswords().map { currentPasswords ->
            currentPasswords.map { entity ->
                PasswordHealth(
                    id = entity.id,
                    name = requireNotNull(entity.title) {
                        "Title is null with ID: ${entity.id}"
                    },
                    email = requireNotNull(entity.email) {
                        "Email is null with ID: ${entity.email}."
                    },
                    image = requireNotNull(entity.image) {
                        "Image is null with ID: ${entity.id}"
                    },
                    type = PasswordType.Strong,
                )
            }
        }
    }

    override fun getReusedPasswords(): Flow<List<PasswordHealth>> {
        return passwordDao.getReusedPasswords().map { currentPasswords ->
            currentPasswords.map { entity ->
                PasswordHealth(
                    id = entity.id,
                    name = requireNotNull(entity.title) {
                        "Title is null with ID: ${entity.id}"
                    },
                    email = requireNotNull(entity.email) {
                        "Email is null with ID: ${entity.email}."
                    },
                    image = requireNotNull(entity.image) {
                        "Image is null with ID: ${entity.id}"
                    },
                    type = PasswordType.Strong,
                )
            }
        }
    }
}
