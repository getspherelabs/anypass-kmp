package io.spherelabs.authimpl.domain

import io.spherelabs.data.settings.keypassword.MasterPasswordSetting
import io.spherelabs.passphraseapi.domain.usecase.SetKeyPasswordUseCase

class DefaultSetKeyPasswordUseCase(
    private val settings: MasterPasswordSetting,
) : SetKeyPasswordUseCase {
    override suspend fun execute(value: String) {
        settings.setMasterPassword(value)
    }
}
