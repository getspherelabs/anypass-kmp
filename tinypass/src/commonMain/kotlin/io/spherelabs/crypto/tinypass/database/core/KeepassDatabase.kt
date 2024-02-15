package io.spherelabs.crypto.tinypass.database.core

import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.tinypass.database.header.InnerHeader
import io.spherelabs.crypto.tinypass.database.header.OuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.DatabaseContent
import io.spherelabs.crypto.tinypass.database.model.component.Group
import io.spherelabs.crypto.tinypass.database.model.component.Meta

data class KeepassDatabase(
    val credentials: Credentials,
    val header: OuterHeader,
    val content: DatabaseContent,
    val innerHeader: InnerHeader,
) {
    companion object {
        fun build(
            credentials: Credentials,
            rootName: String,
            meta: Meta,
        ): KeepassDatabase {
            return KeepassDatabase(
                credentials = credentials,
                header = OuterHeader.create(),
                content = DatabaseContent(
                    meta = meta,
                    group = Group(
                        id = uuid4(),
                        title = rootName,
                        isSearchable = true,
                        isAutoTyped = true,
                    ),
                    deletedObjects = listOf(),
                ),
                innerHeader = InnerHeader.create(),
            )
        }


    }
}
