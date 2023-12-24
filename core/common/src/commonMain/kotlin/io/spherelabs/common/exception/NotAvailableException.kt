package io.spherelabs.common.exception

class NotAvailableException
constructor(
    override val message: String,
) : Exception(message)
