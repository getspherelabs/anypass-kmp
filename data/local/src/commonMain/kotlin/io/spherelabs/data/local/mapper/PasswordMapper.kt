package io.spherelabs.data.local.mapper

import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.model.CategoryDomain
import io.spherelabs.data.local.db.Category
import io.spherelabs.homeapi.models.HomePassword
import io.spherelabs.local.db.PasswordEntity

fun PasswordEntity.asDomain(): AddNewPasswordDomain {
    return AddNewPasswordDomain(
        id = this.id,
        title = this.title ?: "",
        category = this.category_id,
        username = this.username ?: "",
        email = this.email ?: "",
        password = this.password ?: "",
        websiteAddress = this.websiteAddress ?: "",
        notes = this.notes ?: "",
        image = this.image ?: ""
    )
}

fun AddNewPasswordDomain.asEntity(): PasswordEntity {
    return PasswordEntity(
        id = this.id,
        title = this.title,
        category_id = this.category,
        username = this.username,
        email = this.email,
        password = this.password,
        websiteAddress = this.websiteAddress,
        notes = this.notes,
        image = this.image
    )
}

fun Category.asCategoryDomain(): CategoryDomain {
    return when (this) {
        Category.Social -> CategoryDomain.Social
        Category.Browser -> CategoryDomain.Browser
        Category.Payment -> CategoryDomain.Payment
        Category.Unknown -> CategoryDomain.Unknown
    }
}

fun CategoryDomain.asCategory(): Category {
    return when (this) {
        CategoryDomain.Social -> Category.Social
        CategoryDomain.Browser -> Category.Browser
        CategoryDomain.Payment -> Category.Payment
        CategoryDomain.Unknown -> Category.Unknown
    }
}

fun PasswordEntity.asHomeDomain(): HomePassword {
    return HomePassword(
        id = this.id,
        title = this.title ?: "",
        category = this.category_id,
        username = this.username ?: "",
        email = this.email ?: "",
        password = this.password ?: "",
        websiteAddress = this.websiteAddress ?: "",
        notes = this.notes ?: "",
        image = this.image ?: ""
    )
}
