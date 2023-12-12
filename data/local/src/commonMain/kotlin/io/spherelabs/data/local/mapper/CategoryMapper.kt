package io.spherelabs.data.local.mapper


import io.spherelabs.addnewpasswordapi.model.AddNewCategory
import io.spherelabs.homeapi.models.HomeCategory
import io.spherelabs.local.db.Category

fun Category.asDomain(): HomeCategory {
    return HomeCategory(
        id = this.id,
        title = this.title ?: ""
    )
}


fun Category.asNewDomain(): AddNewCategory {
    return AddNewCategory(
        id = this.id,
        title = this.title ?: ""
    )
}
