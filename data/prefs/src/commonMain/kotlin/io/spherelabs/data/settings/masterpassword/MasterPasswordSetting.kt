package io.spherelabs.data.settings.masterpassword

import com.russhwolf.settings.coroutines.FlowSettings

interface MasterPasswordSetting {
    suspend fun setMasterPassword(value: String)
    suspend fun isPasswordExist(): Boolean
    suspend fun getMasterPassword(): String
}

class DefaultMasterPasswordSetting(
    private val prefs: FlowSettings
) : MasterPasswordSetting {

    override suspend fun setMasterPassword(value: String) {
        prefs.putString(MASTER_PASSWORD, value)
    }

    override suspend fun isPasswordExist(): Boolean {
        return prefs.getString(MASTER_PASSWORD, "").isNotEmpty()
    }

    override suspend fun getMasterPassword(): String {
        return prefs.getString(MASTER_PASSWORD, "")
    }

    companion object {
        private const val MASTER_PASSWORD = "master_password"
    }
}