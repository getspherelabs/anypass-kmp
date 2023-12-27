package io.spherelabs.data.settings.keypassword

import com.russhwolf.settings.Settings

expect class EncryptedSettings {
    val settings: Settings
}

interface MasterPasswordSetting {
    suspend fun setMasterPassword(value: String)

    suspend fun isPasswordExist(): Boolean

    suspend fun getMasterPassword(): String
}

class DefaultMasterPasswordSetting(private val prefs: EncryptedSettings) : MasterPasswordSetting {

    override suspend fun setMasterPassword(value: String) {
        prefs.settings.putString(MASTER_PASSWORD, value)
    }

    override suspend fun isPasswordExist(): Boolean {
        return prefs.settings.getString(MASTER_PASSWORD, "").isNotEmpty()
    }

    override suspend fun getMasterPassword(): String {
        return prefs.settings.getString(MASTER_PASSWORD, "")
    }

    companion object {
        private const val MASTER_PASSWORD = "master_password"
    }
}
