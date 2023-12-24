package io.spherelabs.passphraseimpl.domain

import io.spherelabs.data.settings.fingerprint.FingerPrintSetting
import io.spherelabs.passphraseapi.domain.usecase.GetFingerprintStatusUseCase

class DefaultGetFingerprintStatusUseCase(
    private val prefs: FingerPrintSetting,
) : GetFingerprintStatusUseCase {

  override suspend fun execute(): Boolean {
    return prefs.getFingerPrint()
  }
}
