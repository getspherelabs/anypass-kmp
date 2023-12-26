package io.spherelabs.data.settings.repository

import io.spherelabs.changepasswordapi.domain.repository.ChangePasswordRepository
import io.spherelabs.data.settings.keypassword.MasterPasswordSetting

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
