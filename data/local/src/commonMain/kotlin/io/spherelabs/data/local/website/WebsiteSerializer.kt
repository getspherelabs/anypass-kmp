package io.spherelabs.data.local.website

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer

object WebsiteSerializer : JsonContentPolymorphicSerializer<Website>(Website::class) {
    override fun selectDeserializer(element: kotlinx.serialization.json.JsonElement): DeserializationStrategy<out Website> {
        return Website.serializer()
    }
}
