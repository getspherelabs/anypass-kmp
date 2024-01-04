package io.spherelabs.homeimpl.presentation

import androidx.compose.runtime.Stable
import io.spherelabs.homeapi.models.HomeCategory

@Stable
data class UIHomeCategory(val id: String, val title: String)

fun HomeCategory.asUi(): UIHomeCategory {
  return UIHomeCategory(id, title)
}
