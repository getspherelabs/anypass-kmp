package io.spherelabs.addnewpasswordapi

class NotFoundWebsiteException(override val message: String = "Does not found websites"): Exception(message)
