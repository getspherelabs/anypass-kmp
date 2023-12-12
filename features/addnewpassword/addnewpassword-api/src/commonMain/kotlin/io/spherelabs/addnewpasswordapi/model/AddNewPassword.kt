package io.spherelabs.addnewpasswordapi.model

data class AddNewPassword(
  val id: String,
  val title: String = "",
  val email: String = "",
  val category: String = "",
  val username: String = "",
  val password: String = "",
  val websiteAddress: String = "",
  val notes: String = "",
  val image: String = "",
)
