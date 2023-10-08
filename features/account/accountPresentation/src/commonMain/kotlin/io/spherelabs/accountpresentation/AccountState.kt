package io.spherelabs.accountpresentation

import io.spherelabs.accountdomain.repository.AccountUserUi

data class AccountState(
    val sizeOfStrongPassword: Int = 0,
    val sizeOfWeakPassword: Int = 0,
    val sizeOfTotalPassword: Int = 0,
    val isFingerPrintEnabled: Boolean = false,
    val user: AccountUserUi? = null,
) {
    companion object {
        val Empty = AccountState()
    }
}
