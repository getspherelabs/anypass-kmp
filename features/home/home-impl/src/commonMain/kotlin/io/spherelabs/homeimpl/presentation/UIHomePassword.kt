package io.spherelabs.homeimpl.presentation

import androidx.compose.runtime.Stable
import io.spherelabs.homeapi.models.HomePassword

@Stable
data class UIHomePassword(
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

fun HomePassword.asUi(): UIHomePassword {
  return UIHomePassword(
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
