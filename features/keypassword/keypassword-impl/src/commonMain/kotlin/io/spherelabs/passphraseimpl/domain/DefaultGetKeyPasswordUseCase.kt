package io.spherelabs.passphraseimpl.domain

import io.spherelabs.data.settings.keypassword.MasterPasswordSetting
import io.spherelabs.passphraseapi.domain.usecase.GetKeyPasswordUseCase

class DefaultGetKeyPasswordUseCase(private val prefs: MasterPasswordSetting) :
    GetKeyPasswordUseCase {

  override suspend fun execute(): String {
    return prefs.getMasterPassword()
  }
}
