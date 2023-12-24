package io.spherelabs.accountimpl.domain

import io.spherelabs.accountapi.domain.usecase.GetFingerPrintUseCase
import io.spherelabs.data.settings.fingerprint.FingerPrintSetting

class DefaultGetFingerPrintUseCase(
    private val setting: FingerPrintSetting,
) : GetFingerPrintUseCase {

  override suspend fun execute(): Boolean {
    return setting.getFingerPrint()
  }
}
