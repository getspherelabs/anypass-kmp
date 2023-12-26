package io.spherelabs.passphraseimpl.domain

import io.spherelabs.data.settings.keypassword.MasterPasswordSetting
import io.spherelabs.passphraseapi.domain.usecase.SetKeyPasswordUseCase

class DefaultSetKeyPasswordUseCase(private val prefs: MasterPasswordSetting) :
    SetKeyPasswordUseCase {

  override suspend fun execute(value: String) {
    prefs.setMasterPassword(value)
  }
}
