package io.spherelabs.validation

interface WebsiteValidation {
    suspend fun execute(website: String): Boolean
}

class DefaultWebsiteValidation : WebsiteValidation {

    override suspend fun execute(website: String): Boolean {
        return WEBSITE_PATTERN.toRegex().matches(website)
    }

    companion object {
        private const val WEBSITE_PATTERN =
            "^((https?|ftp|file)://)?([a-zA-Z0-9]+(\\\\.[a-zA-Z0-9]+)+.*)\$"
    }
}
