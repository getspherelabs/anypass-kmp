package io.spherelabs.accountpresentation

data class AccountState(
  val sizeOfStrongPassword: Int = 0,
  val sizeOfWeakPassword: Int = 0,
) {
  companion object {
    val Empty = AccountState()
  }
}
