package io.spherelabs.passphraseimpl.domain

import io.spherelabs.data.settings.masterpassword.MasterPasswordSetting
import io.spherelabs.passphraseapi.domain.usecase.IsPasswordExistUseCase


class DefaultIsKeyPasswordExistUseCase(private val prefs: MasterPasswordSetting) :
    IsPasswordExistUseCase {
    override suspend fun execute(): Boolean {
        return prefs.isPasswordExist()
    }
}
