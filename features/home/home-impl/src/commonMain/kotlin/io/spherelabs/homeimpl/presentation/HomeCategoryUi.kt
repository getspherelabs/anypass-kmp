package io.spherelabs.homeimpl.presentation

import io.spherelabs.homeapi.models.HomeCategory


data class HomeCategoryUi(val id: String, val title: String)

fun HomeCategory.asUi(): HomeCategoryUi {
  return HomeCategoryUi(id, title)
}
