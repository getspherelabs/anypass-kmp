package io.spherelabs.addnewpasswordapi.model

data class Websites(
    val list: List<WebsiteDomain>,
)


data class WebsiteDomain(
    val name: String,
    val url: String,
)
