package io.spherelabs.crypto.tinypass.database.model.component

import com.benasher44.uuid.Uuid
import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.common.addUuid
import io.spherelabs.crypto.tinypass.database.common.selectAsInstant
import io.spherelabs.crypto.tinypass.database.common.selectAsUuid
import kotlinx.datetime.Instant

data class DeletedComponent(
    val id: Uuid?,
    val timestamp: Instant,
) {
    fun serialize(): Element {
        return Element(TAG_NAME).apply {
            appendElement(COMPONENT).addUuid(id)
            appendElement(TIMESTAMP).text(timestamp.toString())
        }
    }

    companion object {
        fun deserialize(element: Element): DeletedComponent = with(element) {
            return DeletedComponent(
                id = selectAsUuid(COMPONENT),
                timestamp = selectAsInstant(TIMESTAMP),
            )
        }

        const val TAG_NAME = "DeletedComponents"
        const val COMPONENT = "DeletedComponent"
        const val TIMESTAMP = "DeletionTIme"
    }
}
