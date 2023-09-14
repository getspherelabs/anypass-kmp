package io.spherelabs.data.local.mapper

import io.spherelabs.common.Empty
import io.spherelabs.home.homedomain.model.HomeCategoryDomain
import io.spherelabs.local.db.Category

fun Category.asDomain(): HomeCategoryDomain {
    return HomeCategoryDomain(
        id = this.id,
        title = this.title ?: String.Empty
    )
}