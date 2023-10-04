package io.spherelabs.accountpresentation

sealed interface AccountWish {
    data class GetSizeOfStrongPassword(val size: Int) : AccountWish

    data class GetSizeOfWeakPassword(val size: Int) : AccountWish
    data class GetTotalPassword(val size: Int) : AccountWish
    object GetStartedSizeOfWeakPassword : AccountWish
    object GetStartedSizeOfStrongPassword : AccountWish
    object GetStartedTotalPassword : AccountWish
    data class OpenUrl(val url: String) : AccountWish
    data class Failure(val msg: String) : AccountWish

    data class OnFingerPrintChanged(val isEnabled: Boolean) : AccountWish
    data class SetFingerPrint(val isEnabled: Boolean) : AccountWish
    object GetStartedFingerPrint : AccountWish
    data class GetFingerPrint(val isEnabled: Boolean) : AccountWish
}
