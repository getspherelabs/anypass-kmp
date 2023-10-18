package io.spherelabs.accountdomain.usecase

import io.spherelabs.data.settings.fingerprint.FingerPrintSetting

interface SetFingerPrintUseCase {
  suspend fun execute(newValue: Boolean)
}

class DefaultSetFingerPrintUseCase(
  private val settings: FingerPrintSetting,
) : SetFingerPrintUseCase {

  override suspend fun execute(newValue: Boolean) {
    settings.setFingerPrint(newValue)
  }
}
