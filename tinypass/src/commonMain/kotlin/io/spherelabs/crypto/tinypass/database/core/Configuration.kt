package io.spherelabs.crypto.tinypass.database.core

import com.benasher44.uuid.uuid4
import io.spherelabs.crypto.tinypass.database.header.KdbxInnerHeader
import io.spherelabs.crypto.tinypass.database.header.KdbxOuterHeader
import io.spherelabs.crypto.tinypass.database.model.component.Content
import io.spherelabs.crypto.tinypass.database.model.component.Group
import io.spherelabs.crypto.tinypass.database.model.component.Meta

interface Configuration {
    val kdbxOuterHeader: KdbxOuterHeader
    val kdbxInnerHeader: KdbxInnerHeader
    val content: Content
    val keyHelper: KeyHelper

    public data class Builder(
        val kdbxOuterHeader: KdbxOuterHeader? = null,
        val kdbxInnerHeader: KdbxInnerHeader? = null,
        val content: Content? = null,
        val keyHelper: KeyHelper? = null,
    )

    companion object {
        inline fun create(
            rootName: String,
            meta: Meta,
            block: Configuration.Builder.() -> Unit,
        ): Configuration {
            val builder = Builder().apply(block)


            return object : Configuration {
                override val kdbxOuterHeader: KdbxOuterHeader = checkNotNull(builder.kdbxOuterHeader)
                override val kdbxInnerHeader: KdbxInnerHeader = checkNotNull(builder.kdbxInnerHeader)
                override val content: Content = Content(
                    meta = meta,
                    group = Group(
                        id = uuid4(),
                        title = rootName,
                        isAutoTyped = true,
                        isSearchable = true,
                    ),
                    deletedObjects = listOf(),
                )
                override val keyHelper: KeyHelper = checkNotNull(builder.keyHelper)
            }
        }
    }
}
