package io.spherelabs.home.homepresentation

import io.spherelabs.home.homedomain.model.HomeCategoryDomain

data class HomeCategoryUi(
    val id: String,
    val title: String
)

fun HomeCategoryDomain.asUi(): HomeCategoryUi {
    return HomeCategoryUi(
        id, title
    )
}
