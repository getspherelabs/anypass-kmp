package io.spherelabs.data.local.mapper


import io.spherelabs.addnewpassworddomain.model.AddNewCategoryDomain
import io.spherelabs.homeapi.models.HomeCategory
import io.spherelabs.local.db.Category

fun Category.asDomain(): HomeCategory {
    return HomeCategory(
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
