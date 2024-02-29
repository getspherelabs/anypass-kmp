package io.spherelabs.data.local.website

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

interface WebsiteService {
    suspend fun get(): Result<Website>
}

class DefaultWebsiteService(
    private val reader: FileReader,
) : WebsiteService {

    override suspend fun get(): Result<Website> {
        return withContext(Dispatchers.IO) {
            reader.get().fold(
                onSuccess = {
                    val json = Json.decodeFromString(WebsiteSerializer, it)
                    Result.success(json)
                },
                onFailure = {
                    Result.failure(it)
                },
            )
        }
    }
}


