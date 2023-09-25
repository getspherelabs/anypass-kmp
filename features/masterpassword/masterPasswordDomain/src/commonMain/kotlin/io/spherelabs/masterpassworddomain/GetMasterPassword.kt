package io.spherelabs.masterpassworddomain

import io.spherelabs.data.settings.masterpassword.MasterPasswordSetting

interface GetMasterPassword {
  suspend fun execute(): String
}

class DefaultGetMasterPassword(private val prefs: MasterPasswordSetting) : GetMasterPassword {

  override suspend fun execute(): String {
    return prefs.getMasterPassword()
  }
}
