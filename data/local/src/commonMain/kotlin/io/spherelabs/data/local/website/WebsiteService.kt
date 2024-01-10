package io.spherelabs.data.local.website

interface WebsiteService {
    fun get(): String
}

class DefaultWebsiteService(
    private val reader: FileReader,
) : WebsiteService {
    override fun get(): String {
        return reader.get()
    }
}


