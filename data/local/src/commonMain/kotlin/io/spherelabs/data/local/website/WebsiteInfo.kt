package io.spherelabs.data.local.website

import io.spherelabs.addnewpasswordapi.model.WebsiteDomain
import io.spherelabs.addnewpasswordapi.model.Websites
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

fun Website.toDomain(): Websites {
    return Websites(
        list = this.list.map { it.toDomain() },
    )
}

fun WebsiteInfo.toDomain(): WebsiteDomain {
    return WebsiteDomain(name, url)
}
