package io.spherelabs.data.settings.repository

import io.spherelabs.changepassworddomain.repository.ChangePasswordRepository
import io.spherelabs.data.settings.masterpassword.MasterPasswordSetting

class DefaultChangePasswordRepository(
    private val prefs: MasterPasswordSetting,
) : ChangePasswordRepository {

    override suspend fun getCurrentKeyPassword(): String {
        return prefs.getMasterPassword()
    }

    override suspend fun setNewKeyPassword(newKeyPassword: String) {
        prefs.setMasterPassword(newKeyPassword)
    }
}
