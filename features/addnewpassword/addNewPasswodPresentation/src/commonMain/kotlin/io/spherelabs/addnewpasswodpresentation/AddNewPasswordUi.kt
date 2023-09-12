package io.spherelabs.addnewpasswodpresentation

import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.model.CategoryDomain
import io.spherelabs.common.Empty

data class AddNewPasswordUi(
    val id: String,
    val title: String = String.Empty,
    val email: String = String.Empty,
    val category: CategoryDomain?,
    val username: String = String.Empty,
    val password: String = String.Empty,
    val websiteAddress: String = String.Empty,
    val notes: String = String.Empty,
    val image: String = String.Empty,
)


fun AddNewPasswordUi.asDomain(): AddNewPasswordDomain {
    return AddNewPasswordDomain(
        id = this.id,
        title = this.title,
        category = this.category,
        username = this.username,
        email = this.email,
        password = this.password,
        websiteAddress = this.websiteAddress,
        notes = this.notes,
        image = this.image
    )
}