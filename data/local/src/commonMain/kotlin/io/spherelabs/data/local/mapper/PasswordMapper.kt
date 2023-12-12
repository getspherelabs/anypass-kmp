package io.spherelabs.data.local.mapper

import io.spherelabs.addnewpasswordapi.model.AddNewPassword
import io.spherelabs.addnewpasswordapi.model.Category as DomainCategory
import io.spherelabs.data.local.db.Category
import io.spherelabs.homeapi.models.HomePassword
import io.spherelabs.local.db.PasswordEntity

fun PasswordEntity.asDomain(): AddNewPassword {
    return AddNewPassword(
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

fun AddNewPassword.asEntity(): PasswordEntity {
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

fun Category.asCategoryDomain(): DomainCategory {
    return when (this) {
        Category.Social -> DomainCategory.Social
        Category.Browser -> DomainCategory.Browser
        Category.Payment -> DomainCategory.Payment
        Category.Unknown -> DomainCategory.Unknown
    }
}

fun DomainCategory.asCategory(): Category {
    return when (this) {
        DomainCategory.Social -> Category.Social
        DomainCategory.Browser -> Category.Browser
        DomainCategory.Payment -> Category.Payment
        DomainCategory.Unknown -> Category.Unknown
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
