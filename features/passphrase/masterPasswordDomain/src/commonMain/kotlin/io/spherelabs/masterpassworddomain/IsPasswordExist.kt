package io.spherelabs.masterpassworddomain

import io.spherelabs.data.settings.masterpassword.MasterPasswordSetting

interface IsPasswordExist {
  suspend fun execute(): Boolean
}

class DefaultIsPasswordExist(private val prefs: MasterPasswordSetting) : IsPasswordExist {
  override suspend fun execute(): Boolean {
    return prefs.isPasswordExist()
  }
}
