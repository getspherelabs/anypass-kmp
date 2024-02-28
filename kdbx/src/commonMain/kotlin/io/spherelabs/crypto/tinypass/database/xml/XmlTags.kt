package io.spherelabs.crypto.tinypass.database.xml

object XmlTags {
    const val UUID = "uuid"
    const val ROOT = "root"
    /**
     * Meta
     */
    const val META_TAG_NAME = "meta"
    const val META_GENERATOR = "generator"
    const val META_HEADER_HASH = "headerhash"
    const val META_SETTINGS_CHANGED = "settingschanged"
    const val META_DATABASE_NAME = "database_name"
    const val META_DATABASE_NAME_CHANGED = "database_name_changed"
    const val DatabaseDescription = "DatabaseDescription"
    const val DatabaseDescriptionChanged = "DatabaseDescriptionChanged"
    const val DefaultUserName = "DefaultUserName"
    const val DefaultUserNameChanged = "DefaultUserNameChanged"
    const val MaintenanceHistoryDays = "MaintenanceHistoryDays"
    const val Color = "Color"
    const val MasterKeyChanged = "MasterKeyChanged"
    const val MasterKeyChangeRec = "MasterKeyChangeRec"
    const val MasterKeyChangeForce = "MasterKeyChangeForce"
    const val RecycleBinEnabled = "RecycleBinEnabled"
    const val RecycleBinUuid = "RecycleBinUUID"
    const val RecycleBinChanged = "RecycleBinChanged"
    const val EntryTemplatesGroup = "EntryTemplatesGroup"
    const val EntryTemplatesGroupChanged = "EntryTemplatesGroupChanged"
    const val HistoryMaxItems = "HistoryMaxItems"
    const val HistoryMaxSize = "HistoryMaxSize"
    const val LastSelectedGroup = "LastSelectedGroup"
    const val LastTopVisibleGroup = "LastTopVisibleGroup"

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
    const val CUSTOM_DATA_TAG_NAME = "CustomData"
    const val CUSTOM_DATA_ITEM = "Item"
    const val CUSTOM_DATA_KEY = "Key"
    const val CUSTOM_DATA_VALUE = "Value"
}
