package io.spherelabs.homeapi.models

data class HomePassword(
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
