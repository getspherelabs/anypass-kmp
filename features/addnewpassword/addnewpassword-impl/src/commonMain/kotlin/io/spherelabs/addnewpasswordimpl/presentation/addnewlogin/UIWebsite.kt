package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import io.spherelabs.addnewpasswordapi.model.WebsiteDomain

data class UIWebsite(
    val name: String,
    val url: String,
) : JavaSerializable {
    fun contains(query: String): Boolean {
        return name.contains(query, ignoreCase = true)
    }
}

fun WebsiteDomain.toUI(): UIWebsite {
    return UIWebsite(name, url)
}
