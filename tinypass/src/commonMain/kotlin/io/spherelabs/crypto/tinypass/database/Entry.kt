package io.spherelabs.crypto.tinypass.database

import com.benasher44.uuid.Uuid
import kotlinx.datetime.Instant
import okio.ByteString

/**
 * On the mobile side, do not need auto type.
 */
data class Entry(
    override val uuid: Uuid,
    override val icon: PredefinedIcon = PredefinedIcon.Key,
    override val customIconUuid: Uuid? = null,
    val foregroundColor: String? = null,
    val backgroundColor: String? = null,
    val overrideUrl: String = "",
    override val times: TimeData? = TimeData.create(),
    val autoType: AutoTypeData? = null,
    val fields: EntryFields = EntryFields.createDefault(),
    override val tags: List<String> = listOf(),
    val binaries: List<BinaryReference> = listOf(),
    val history: List<Entry> = listOf(),
    val customData: Map<String, CustomDataValue> = mapOf(),
    val previousParentGroup: Uuid? = null,
    val qualityCheck: Boolean = true,
) : DatabaseElement {
    operator fun get(field: BasicField): EntryValue? = fields[field()]
}


data class BinaryReference(
    val hash: ByteString,
    val name: String,
)


/**
 * Arbitrary string data holder for database/group/entry metadata.
 */
data class CustomDataValue(
    val value: String,
    val lastModified: Instant? = null,
)
