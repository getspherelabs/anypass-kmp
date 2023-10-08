package io.spherelabs.accountdomain.repository

import io.spherelabs.data.settings.fingerprint.FingerPrintSetting

interface SetFingerPrint {
  suspend fun execute(newValue: Boolean)
}

class DefaultSetFingerPrint(
  private val settings: FingerPrintSetting,
) : SetFingerPrint {

  override suspend fun execute(newValue: Boolean) {
    settings.setFingerPrint(newValue)
  }
}
