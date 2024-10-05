package io.spherelabs.crypto.tinypass.database.entity

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.tinypass.database.entity.autotype.AutoType
import io.spherelabs.crypto.tinypass.database.model.component.EntryFields
import io.spherelabs.crypto.tinypass.database.model.component.PredefinedIcon
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Entry(
    override val id: Uuid,
    override val expiredAt: Instant? = null,
    override val expired: Boolean = false,
    override val createdAt: Instant = Clock.System.now(),
    override val lastModifiedAt: Instant = Clock.System.now(),
    override val icon: PredefinedIcon,
    override val customIconUuid: Uuid?,
    override val tags: List<String>,
    val foregroundColor: String? = null,
    val backgroundColor: String? = null,
    val overrideUrl: String = "",
    val binaries: List<BinaryReference> = listOf(),
    val autoType: AutoType? = null,
    val fields: EntryFields = EntryFields.Default,
    val history: List<Entry> = listOf(),
    val customData: Map<String, CustomDataValue> = mapOf(),
    val previousParentGroup: Uuid? = null,
    val qualityCheck: Boolean = true,
) : Entity
