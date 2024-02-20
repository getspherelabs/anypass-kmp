package io.spherelabs.crypto.tinypass.database.model.component

import com.benasher44.uuid.Uuid
import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.common.*
import io.spherelabs.crypto.tinypass.database.common.addBytes
import io.spherelabs.crypto.tinypass.database.common.addUuid
import io.spherelabs.crypto.tinypass.database.common.getInstant
import io.spherelabs.crypto.tinypass.database.common.selectAsUuid
import io.spherelabs.crypto.tinypass.database.core.XmlContext
import io.spherelabs.crypto.tinypass.database.xml.XmlOption
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlinx.datetime.Instant
import okio.ByteString.Companion.decodeBase64

data class CustomIcon(
    val data: ByteArray,
    val name: String?,
    val lastModified: Instant?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is CustomIcon) return false

        other as CustomIcon

        if (!data.contentEquals(other.data)) return false
        if (name != other.name) return false
        if (lastModified != other.lastModified) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + lastModified.hashCode()
        return result
    }

}

internal object CustomIcons {
    const val TagName = "CustomIcons"
    const val Item = "Icon"
    const val ItemUuid = "Uuid"
    const val ItemData = "Data"
    const val ItemName = "Name"

    @OptIn(ExperimentalEncodingApi::class)
    fun deserialize(element: Element): Map<Uuid, CustomIcon> = with(element) {
        val tag = getElementsByTag("Item")
        return tag.associate {
            val id = it.selectAsUuid(ItemUuid)
            val data = Base64.decode(it.select(ItemData).text())
            val name = it.select(ItemName).text()
            val lastModified = it.select("LastModificationTime").getInstant()

            checkNotNull(id)

            id to CustomIcon(data = data, name, lastModified)
        }
    }

    fun serialize(context: XmlOption, customicon: Map<Uuid, CustomIcon>): Element {
        return Element("CustomIcon").apply {
            for ((key, item) in customicon) {
                appendElement(Item).apply {
                    appendElement(ItemUuid).addUuid(key)
                    appendElement(ItemData).addBytes(item.data)

                    if (context.version.isAtLeast(4, 1)) {
                        appendElement(ItemName).text(item.name.toString())
                        appendElement("LastModificationTime").text(
                            checkNotNull(item.lastModified?.deserialize(context)),
                        )
                    }
                }
            }

        }
    }
}
