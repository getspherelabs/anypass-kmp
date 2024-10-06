package io.spherelabs.crypto.kdbx.database.xml

object XmlTags {
    const val UUID = "uuid"
    const val ROOT = "root"
    const val LAST_MODIFICATION_TIME = "last_modification_time"

    /**
     * Meta
     */
    const val META_TAG_NAME = "meta"
    const val META_GENERATOR = "generator"
    const val META_HEADER_HASH = "header_hash"
    const val META_SETTINGS_CHANGED = "settings_changed"
    const val META_DATABASE_NAME = "database_name"
    const val META_DATABASE_NAME_CHANGED = "database_name_changed"
    const val META_DATABASE_DESCRIPTION = "database_description"
    const val META_DATABASE_DESCRIPTION_CHANGED = "database_description_changed"
    const val META_DEFAULT_USER_NAME = "default_user_name"
    const val META_DEFAULT_USER_NAME_CHANGED = "default_user_name_changed"
    const val META_MAINTENANCE_HISTORY_DAYS = "maintenance_history_days"
    const val META_COLOR = "color"
    const val META_MASTER_KEY_CHANGED = "master_key_changed"
    const val META_MASTER_KEY_CHANGED_RECORD = "master_key_change_record"
    const val META_MASTER_KEY_CHANGE_FORCE = "master_key_change_force"
    const val META_RECYCLE_BIN_ENABLED = "recycle_bin_enabled"
    const val META_RECYCLE_BIN_UUID = "recycle_bin_uuid"
    const val META_RECYCLE_BIN_CHANGED = "recycle_bin_changed"
    const val META_ENTRY_TEMPLATES_GROUP = "entry_templates_group"
    const val META_ENTRY_TEMPLATES_GROUP_CHANGED = "entry_templates_group_changed"
    const val META_HISTORY_MAX_ITEMS = "history_max_items"
    const val META_HISTORY_MAX_SIZE = "history_max_size"
    const val META_LAST_SELECTION_GROUP = "last_selected_group"
    const val META_LAST_TOP_VISIBLE_GROUP = "last_top_visible_group"

    const val EXPIRED_AT = "expired_at"
    const val EXPIRED = "expired"
    const val CREATED_AT = "created_at"
    const val LAST_MODIFIED_AT = "last_modified_at"

    /**
     * Group
     */

    /**
     *     override val id: Uuid,
     *     override val expiredAt: Instant? = null,
     *     override val expired: Boolean = false,
     *     override val createdAt: Instant = Instant.Default,
     *     override val lastModifiedAt: Instant = Instant.Default,
     *     override val tags: List<String> = listOf(),
     *     override val icon: PredefinedIcon = PredefinedIcon.Folder,
     *     override val customIconUuid: Uuid? = null,
     *     val name: String,
     *     val notes: String = "",
     *     val isSearchable: Boolean = false,
     *     val isAutoTyped: Boolean = false,
     *     val isExpanded: Boolean = true,
     *     val typeSequence: String? = null,
     *     val lastTopVisibleEntryId: Uuid? = null,
     *     val previousParentGroupId: Uuid? = null,
     *     val childGroups: List<Group> = emptyList(),
     *     val entries: List<Entry> = emptyList(),
     *     val customData: Map<String, CustomDataValue> = mapOf(),
     */
    const val GROUP_TAG_NAME = "Group"
    const val GROUP_NAME = "name"
    const val GROUP_NOTES = "notes"
    const val GROUP_ICON_ID = "icon_id"
    const val GROUP_CUSTOM_ICON_UUID = "custom_icon_uuid"
    const val GROUP_TAGS = "tags"
    const val GROUP_IS_EXPANDED = "is_expanded"
    const val GROUP_DEFAULT_AUTO_TYPE_SEQUENCE = "DefaultAutoTypeSequence"
    const val GROUP_ENABLE_AUTO_TYPE = "EnableAutoType"
    const val GROUP_ENABLE_SEARCHING = "EnableSearching"
    const val GROUP_LAST_TOP_VISIBLE_ENTRY = "LastTopVisibleEntry"
    const val GROUP_PREVIOUS_PARENT_GROUP = "PreviousParentGroup"


//
//    object AutoType {
//        const val TagName = "AutoType"
//        const val Enabled = "Enabled"
//        const val Obfuscation = "DataTransferObfuscation"
//        const val DefaultSequence = "DefaultSequence"
//        const val Association = "Association"
//        const val Window = "Window"
//        const val KeystrokeSequence = "KeystrokeSequence"
//    }

    /**
     * Entry
     */
    const val ENTRY_TAG_NAME = "Entry"
    const val ENTRY_ICON_ID = "icon_id"
    const val ENTRY_CUSTOM_ICON_ID = "CustomIconUUID"
    const val ENTRY_FOREGROUND_COLOR = "ForegroundColor"
    const val ENTRY_BACKGROUND_COLOR = "BackgroundColor"
    const val ENTRY_OVERRIDE_URL = "override_url"
    const val ENTRY_TAGS = "Tags"
    const val ENTRY_HISTORY = "History"
    const val ENTRY_QUALITY_CHECK = "QualityCheck"

    /**
     * Entry Fields
     */
    const val ENTRY_FIELDS_TAG_NAME = "String"
    const val ENTRY_FIELDS_ITEM_KEY = "key"
    const val ENTRY_FIELDS_ITEM_VALUE = "value"

    /**
     * Entry Binary References
     */
    const val ENTRY_BINARY_REFERENCES_TAG_NAME ="Binary"
    const val ENTRY_BINARY_REFERENCES_ITEM_KEY = "Key"
    const val ENTRY_BINARY_REFERENCES_ITEM_VALUE = "Value"


    /**
     * Custom Data
     */
    const val CUSTOM_DATA_TAG_NAME = "custom_data"
    const val CUSTOM_DATA_ITEM = "item"
    const val CUSTOM_DATA_KEY = "key"
    const val CUSTOM_DATA_VALUE = "value"

    /**
     * Custom Icon
     */
    const val ICON_TAG_NAME = "custom_icons"
    const val ICON_ITEM = "item"
    const val ICON_UUID = "uuid"
    const val ICON_DATA = "data"
    const val ICON_NAME = "name"

    /**
     * Memory Protection Flags
     */
    const val MEMORY_PROTECTION_TAG_NAME = "memory_protection"
    const val MEMORY_PROTECTION_NAME = "protection_name"
    const val MEMORY_PROTECTION_USERNAME = "protection_username"
    const val MEMORY_PROTECTION_PASSWORD = "protection_password"
    const val MEMORY_PROTECTION_URL = "protection_url"
    const val MEMORY_PROTECTION_NOTES = "protection_notes"

    /**
     * Binary Data
     */
    const val BINARY_TAG_NAME = "binary"
    const val BINARY_DATA_ID = "id"
    const val BINARY_DATA_COMPRESSED = "compressed"
    const val BINARY_DATA_BINARY = "binary"
    const val BINARY_DATA_BINARIES = "binaries"
}
