package io.spherelabs.addnewpasswodpresentation

import io.spherelabs.addnewpassworddomain.model.AddNewCategoryDomain

data class AddNewCategoryUi(val id: String, val title: String)

fun AddNewCategoryDomain.asUi(): AddNewCategoryUi {
  return AddNewCategoryUi(
    id,
    title,
  )
}
