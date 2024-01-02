package io.spherelabs.authimpl.domain

import io.spherelabs.authapi.domain.usecase.SetKeyPasswordUseCase
import io.spherelabs.data.settings.keypassword.MasterPasswordSetting

class DefaultSetKeyPasswordUseCase(
    private val settings: MasterPasswordSetting,
) : SetKeyPasswordUseCase {
    override suspend fun execute(value: String) {
        settings.setMasterPassword(value)
    }
}
