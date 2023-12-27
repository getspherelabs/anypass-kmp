package io.spherelabs.accountimpl.presentation

import androidx.compose.runtime.Immutable
import io.spherelabs.accountapi.model.AccountUser

@Immutable
data class AccountState(
    val sizeOfStrongPassword: Int = 0,
    val sizeOfWeakPassword: Int = 0,
    val sizeOfTotalPassword: Int = 0,
    val isFingerPrintEnabled: Boolean = false,
    val isRestrictScreenshotEnabled: Boolean = false,
    val user: AccountUser? = null,
    val isLogout: Boolean = false
) {
  companion object {
    val Empty = AccountState()
  }
}
