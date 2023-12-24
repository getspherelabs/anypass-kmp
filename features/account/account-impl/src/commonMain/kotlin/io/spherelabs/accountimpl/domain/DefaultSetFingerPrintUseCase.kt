package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.usecase.SetFingerPrintUseCase
import io.spherelabs.data.settings.fingerprint.FingerPrintSetting

class DefaultSetFingerPrintUseCase(
    private val settings: FingerPrintSetting,
) : SetFingerPrintUseCase {

  override suspend fun execute(newValue: Boolean) {
    settings.setFingerPrint(newValue)
  }
}
