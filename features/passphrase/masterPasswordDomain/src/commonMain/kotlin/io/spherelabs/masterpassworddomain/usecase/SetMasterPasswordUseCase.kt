package io.spherelabs.masterpassworddomain.usecase

import io.spherelabs.data.settings.masterpassword.MasterPasswordSetting

interface SetMasterPasswordUseCase {
  suspend fun execute(value: String)
}

class DefaultSetMasterPasswordUseCase(private val prefs: MasterPasswordSetting) : SetMasterPasswordUseCase {

  override suspend fun execute(value: String) {
    prefs.setMasterPassword(value)
  }
}
