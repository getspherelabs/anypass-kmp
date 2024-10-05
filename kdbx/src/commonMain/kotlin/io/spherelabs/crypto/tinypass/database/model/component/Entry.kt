package io.spherelabs.crypto.tinypass.database.model.component

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.tinypass.database.BasicField
import io.spherelabs.crypto.tinypass.database.entity.BinaryReference
import io.spherelabs.crypto.tinypass.database.entity.CustomDataValue
import io.spherelabs.crypto.tinypass.database.entity.autotype.AutoType

data class Entry(
    override val uuid: Uuid,
    override val icon: PredefinedIcon = PredefinedIcon.Key,
    override val customIconUuid: Uuid? = null,
    override val times: TimeData? = TimeData.create(),
    val foregroundColor: String? = null,
    val backgroundColor: String? = null,
    val overrideUrl: String = "",
    val autoType: AutoType? = null,
    val fields: EntryFields = EntryFields.Default,
    override val tags: List<String> = listOf(),
    val binaries: List<BinaryReference> = listOf(),
    val history: List<Entry> = listOf(),
    val customData: Map<String, CustomDataValue> = mapOf(),
    val previousParentGroup: Uuid? = null,
    val qualityCheck: Boolean = true,
) : DatabaseElement {
    operator fun get(field: BasicField): EntryValue? = fields[field()]
}
