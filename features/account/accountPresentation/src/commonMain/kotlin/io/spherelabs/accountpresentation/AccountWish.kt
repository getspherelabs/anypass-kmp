package io.spherelabs.accountpresentation

sealed interface AccountWish {
  data class GetSizeOfStrongPassword(val size: Int) : AccountWish

  data class GetSizeOfWeakPassword(val size: Int) : AccountWish

  object GetStartedSizeOfPassword : AccountWish
}
