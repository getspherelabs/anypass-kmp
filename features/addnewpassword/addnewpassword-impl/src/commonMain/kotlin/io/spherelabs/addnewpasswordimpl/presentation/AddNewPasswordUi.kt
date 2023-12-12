package io.spherelabs.addnewpasswordimpl.presentation

import io.spherelabs.addnewpasswordapi.model.AddNewPassword
import io.spherelabs.common.Empty

data class AddNewPasswordUi(
  val id: String,
  val title: String = String.Empty,
  val email: String = String.Empty,
  val category: String?,
  val username: String = String.Empty,
  val password: String = String.Empty,
  val websiteAddress: String = String.Empty,
  val notes: String = String.Empty,
  val image: String = String.Empty,
)

fun AddNewPasswordUi.asDomain(): AddNewPassword {
  return AddNewPassword(
    id = this.id,
    title = this.title,
    category = this.category ?: String.Empty,
    username = this.username,
    email = this.email,
    password = this.password,
    websiteAddress = this.websiteAddress,
    notes = this.notes,
    image = this.image
  )
}
