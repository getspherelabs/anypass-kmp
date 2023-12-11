package io.spherelabs.homeimpl.presentation

import io.spherelabs.homeapi.models.HomePassword

data class HomePasswordUi(
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

fun HomePassword.asUi(): HomePasswordUi {
  return HomePasswordUi(
    id,
    title,
    email,
    category,
    username,
    password,
    websiteAddress,
    notes,
    image,
  )
}
