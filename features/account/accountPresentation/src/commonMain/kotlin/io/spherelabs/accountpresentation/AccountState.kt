package io.spherelabs.accountpresentation

data class AccountState(
  val sizeOfStrongPassword: Int = 0,
  val sizeOfWeakPassword: Int = 0,
  val sizeOfTotalPassword: Int = 0,
  val isFingerPrintEnabled: Boolean = false,
) {
  companion object {
    val Empty = AccountState()
  }
}
