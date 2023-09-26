package io.spherelabs.addnewpasswodpresentation

import io.spherelabs.common.Empty

data class AddNewPasswordState(
  val email: String = String.Empty,
  val password: String = String.Empty,
  val title: String = String.Empty,
  val categories: List<AddNewCategoryUi> = emptyList(),
  val currentCategory: String = String.Empty,
  val username: String = String.Empty,
  val websiteAddress: String = String.Empty,
  val notes: String = String.Empty,
  val image: String = String.Empty,
) {
  companion object {
    val Empty = AddNewPasswordState()
  }
}
