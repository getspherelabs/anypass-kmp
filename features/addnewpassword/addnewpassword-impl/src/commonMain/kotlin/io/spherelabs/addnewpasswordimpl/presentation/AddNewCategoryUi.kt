package io.spherelabs.addnewpasswordimpl.presentation

import io.spherelabs.addnewpasswordapi.model.AddNewCategory

data class AddNewCategoryUi(val id: String, val title: String)

fun AddNewCategory.asUi(): AddNewCategoryUi {
  return AddNewCategoryUi(
    id,
    title,
  )
}
