package io.spherelabs.crypto.tinypass.database.model.component

import com.benasher44.uuid.Uuid
import io.spherelabs.crypto.tinypass.database.FormatXml
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import okio.ByteString

/**
 * Meta describes various database meta data.
 * Note that *MasterKeyChangeForceOnce* is omitted.
 *
 * @property generator Application name that generated database file.
 * @property headerHash The SHA-256 hash of the database header data (version 3.x).
 * @property settingsChanged Timestamp of the last change of database settings.
 * @property name Database name.
 * @property nameChanged Timestamp of the last change of [name].
 * @property description Database description.
 * @property descriptionChanged Timestamp of the last change of [description].
 * @property defaultUser Username to use as a default when creating a new entry in the database.
 * @property defaultUserChanged Timestamp of the last change of [defaultUser].
 * @property maintenanceHistoryDays Indicates the maximum age of the oldest history item
 * to keep in days when performing database maintenance.
 * @property color Can be used to show a colored database icon.
 * @property masterKeyChanged Timestamp of the last change of the database’s master key.
 * @property masterKeyChangeRec Indicates the number of days after which application should
 * recommend changing the database’s master key (-1 means never).
 * @property masterKeyChangeForce Indicates the number of days after which application should
 * force changing the database’s master key (-1 means never).
 * @property recycleBinEnabled Indicates whether database should use the recycle bin.
 * @property recycleBinUuid [Uuid] of the recycle bin group inside the database.
 * @property recycleBinChanged Timestamp of the last change of [recycleBinUuid].
 * @property entryTemplatesGroup [Uuid] of the group that holds templates for the new entries.
 * @property entryTemplatesGroupChanged Timestamp of the last change of [entryTemplatesGroup].
 * @property historyMaxItems Maximum number of historic items [Entry] should store.
 * @property historyMaxSize Indicates maximum history size in bytes.
 * @property lastSelectedGroup [Uuid] of the group that was last selected by the user.
 * @property lastTopVisibleGroup [Uuid] of the top-most group that is visible in the user’s last session.
 * @property memoryProtection Indicates which fields should be protected by in-memory encryption at runtime.
 * @property customIcons Collection of attached binary files which could be used as custom icons.
 * @property customData Used by plugins to store arbitrary string data at database level.
 * @property binaries Collection of attached binary files (version 3.x).
 */
data class Meta(
    val generator: String = Defaults.Generator,
    val headerHash: ByteString? = null,
    val settingsChanged: Instant? = Clock.System.now(),
    val name: String = "",
    val nameChanged: Instant? = Clock.System.now(),
    val description: String = "",
    val descriptionChanged: Instant? = Clock.System.now(),
    val defaultUser: String = "",
    val defaultUserChanged: Instant? = Clock.System.now(),
    val maintenanceHistoryDays: Int = Defaults.MaintenanceHistoryDays,
    val color: String? = null,
    val masterKeyChanged: Instant? = null,
    val masterKeyChangeRec: Int = -1,
    val masterKeyChangeForce: Int = -1,
    val recycleBinEnabled: Boolean = false,
    val recycleBinUuid: Uuid? = null,
    val recycleBinChanged: Instant? = null,
    val entryTemplatesGroup: Uuid? = null,
    val entryTemplatesGroupChanged: Instant? = null,
    val historyMaxItems: Int = Defaults.HistoryMaxItems,
    val historyMaxSize: Int = Defaults.HistoryMaxSize,
    val lastSelectedGroup: Uuid? = null,
    val lastTopVisibleGroup: Uuid? = null,
    val memoryProtection: Set<MemoryProtectionFlag> = setOf(MemoryProtectionFlag.Password),
    val customIcons: Map<Uuid, CustomIcon> = mapOf(),
    val customData: Map<String, CustomDataValue> = mapOf(),
    @PublishedApi
    internal val binaries: Map<ByteString, BinaryData> = linkedMapOf()
)

internal object Defaults {
    const val Generator = "TinyPass"
    const val PlaceholdersMaxDepth = 10U
    const val MaintenanceHistoryDays = 365
    const val HistoryMaxItems = 10
    const val HistoryMaxSize = 6 * 1024 * 1024
    const val RecycleBinName = "Recycle Bin"
    const val UntitledLabel = "Untitled"
}

enum class MemoryProtectionFlag(val value: String) {
    Title(FormatXml.Tags.Meta.MemoryProtection.ProtectTitle),
    UserName(FormatXml.Tags.Meta.MemoryProtection.ProtectUserName),
    Password(FormatXml.Tags.Meta.MemoryProtection.ProtectPassword),
    Url(FormatXml.Tags.Meta.MemoryProtection.ProtectUrl),
    Notes(FormatXml.Tags.Meta.MemoryProtection.ProtectNotes)
}
