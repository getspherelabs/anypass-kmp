package io.spherelabs.masterpassworddomain.usecase

import io.spherelabs.data.settings.masterpassword.MasterPasswordSetting

interface IsPasswordExistUseCase {
  suspend fun execute(): Boolean
}

class DefaultIsPasswordExistUseCase(private val prefs: MasterPasswordSetting) : IsPasswordExistUseCase {
  override suspend fun execute(): Boolean {
    return prefs.isPasswordExist()
  }
}
