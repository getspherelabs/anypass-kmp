package io.spherelabs.data.local.website

import kotlinx.serialization.Serializable

@Serializable
data class Website(
    val list: List<WebsiteInfo>,
)

@Serializable
data class WebsiteInfo(
    val name: String,
    val url: String,
)
