package io.spherelabs.accountimpl.presentation

import io.spherelabs.accountapi.model.AccountUser


data class AccountState(
    val sizeOfStrongPassword: Int = 0,
    val sizeOfWeakPassword: Int = 0,
    val sizeOfTotalPassword: Int = 0,
    val isFingerPrintEnabled: Boolean = false,
    val user: AccountUser? = null,
) {
    companion object {
        val Empty = AccountState()
    }
}
