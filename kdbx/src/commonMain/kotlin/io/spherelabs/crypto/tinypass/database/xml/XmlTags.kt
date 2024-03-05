package io.spherelabs.crypto.tinypass.database.xml

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

    /**
     * Group
     */
    const val GROUP_TAG_NAME = "Group"
    const val GROUP_NAME = "Name"
    const val GROUP_NOTES = "Notes"
    const val GROUP_ICON_ID = "IconID"
    const val GROUP_CUSTOM_ICON_UUID = "CustomIconUUID"
    const val GROUP_TAGS = "Tags"
    const val GROUP_IS_EXPANDED = "IsExpanded"
    const val GROUP_DEFAULT_AUTO_TYPE_SEQUENCE = "DefaultAutoTypeSequence"
    const val GROUP_ENABLE_AUTO_TYPE = "EnableAutoType"
    const val GROUP_ENABLE_SEARCHING = "EnableSearching"
    const val GROUP_LAST_TOP_VISIBLE_ENTRY = "LastTopVisibleEntry"
    const val GROUP_PREVIOUS_PARENT_GROUP = "PreviousParentGroup"

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
