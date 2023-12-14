package io.spherelabs.accountimpl.presentation

import io.spherelabs.accountapi.model.AccountUser
import io.spherelabs.accountimpl.domain.model.AllPasswords


sealed interface AccountWish {
    data class GetSizeOfStrongPassword(val size: Int) : AccountWish

    data class GetSizeOfWeakPassword(val size: Int) : AccountWish

    data class GetTotalPassword(val size: Int) : AccountWish

    data class OpenUrl(val url: String) : AccountWish

    data class Failure(val msg: String) : AccountWish

    data class OnFingerPrintChanged(val isEnabled: Boolean) : AccountWish

    data class SetFingerPrint(val isEnabled: Boolean) : AccountWish

    object GetStartedFingerPrint : AccountWish

    data class GetFingerPrint(val isEnabled: Boolean) : AccountWish
    object GetAccount : AccountWish
    data class GetUser(val user: AccountUser) : AccountWish
    object NavigateToChangePassword : AccountWish
    object NavigateToBack : AccountWish
    data class OnGetAllPasswords(val allPasswords: AllPasswords) : AccountWish
}
