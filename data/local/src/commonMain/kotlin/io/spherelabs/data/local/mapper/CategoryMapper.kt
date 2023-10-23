package io.spherelabs.data.local.mapper


import io.spherelabs.addnewpassworddomain.model.AddNewCategoryDomain
import io.spherelabs.home.homedomain.model.HomeCategoryDomain
import io.spherelabs.local.db.Category

fun Category.asDomain(): HomeCategoryDomain {
    return HomeCategoryDomain(
        id = this.id,
        title = this.title ?: ""
    )
}


fun Category.asNewDomain(): AddNewCategoryDomain {
    return AddNewCategoryDomain(
        id = this.id,
        title = this.title ?: ""
    )
}
