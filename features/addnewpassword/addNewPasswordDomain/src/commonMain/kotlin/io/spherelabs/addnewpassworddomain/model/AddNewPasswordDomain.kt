package io.spherelabs.addnewpassworddomain.model


data class AddNewPasswordDomain(
    val id: String,
    val title: String = "",
    val email: String = "",
    val category: String = "",
    val username: String = "",
    val password: String = "",
    val websiteAddress: String = "",
    val notes: String = "",
    val image: String = "",
)
