package io.spherelabs.addnewpassworddomain.model

import io.spherelabs.common.Empty

data class AddNewPasswordDomain(
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
