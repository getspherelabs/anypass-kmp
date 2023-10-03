package io.spherelabs.home.homepresentation

import io.spherelabs.home.homedomain.model.HomePasswordDomain

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

fun HomePasswordDomain.asUi(): HomePasswordUi {
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
