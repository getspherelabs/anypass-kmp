package io.spherelabs.crypto.tinypass.database

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

data class Group(
    override val uuid: Uuid,
    val name: String,
    val notes: String = "",
    override val icon: PredefinedIcon = PredefinedIcon.Folder,
    override val customIconUuid: Uuid? = null,
    override val times: TimeData? = TimeData.create(),
    val expanded: Boolean = true,
    val defaultAutoTypeSequence: String? = null,
    val enableAutoType: GroupOverride = GroupOverride.Inherit,
    val enableSearching: GroupOverride = GroupOverride.Inherit,
    val lastTopVisibleEntry: Uuid? = null,
    val previousParentGroup: Uuid? = null,
    override val tags: List<String> = listOf(),
    val groups: List<Group> = listOf(),
    val entries: List<Entry> = listOf(),
    val customData: Map<String, CustomDataValue> = mapOf(),
) : DatabaseElement {
    fun traverse(
        block: (DatabaseElement) -> Unit,
    ) {
        val stack = Stack<Group>()
        stack.push(this)

        while (!stack.isEmpty) {
            val current = stack.pop()!!
            block(current)

            for (entry in current.entries) {
                block(entry)
            }
            for (group in current.groups) {
                stack.push(group)
            }
        }
    }

    fun findChildGroup(
        recycleBinUuid: Uuid? = null,
        predicate: (Group) -> Boolean,
    ): Pair<Group, Group>? {
        val stack = Stack<Pair<Group, Group>>()
        groups
            .filter { recycleBinUuid == null || it.uuid.compareTo(recycleBinUuid) != 0 }
            .forEach { stack.push(this to it) }

        while (!stack.isEmpty) {
            val (parent, current) = stack.pop()!!

            if (predicate(current)) {
                return parent to current
            }
            current.groups
                .filter { recycleBinUuid == null || it.uuid.compareTo(recycleBinUuid) != 0 }
                .forEach { stack.push(current to it) }
        }

        return null
    }

    fun findChildEntry(
        useGroupOverride: Boolean = false,
        recycleBinUuid: Uuid? = null,
        predicate: (Entry) -> Boolean,
    ): Pair<Group, Entry>? {
        val stack = Stack<Pair<Group, Boolean>>()
        stack.push(this to true)

        while (!stack.isEmpty) {
            val (current, parentSearchEnabled) = stack.pop()!!

            val searchEnabled = when (current.enableSearching) {
                GroupOverride.Inherit -> parentSearchEnabled
                GroupOverride.Enabled -> true
                GroupOverride.Disabled -> false
            }
            if (!useGroupOverride || searchEnabled) {
                for (entry in current.entries) {
                    if (predicate(entry)) {
                        return current to entry
                    }
                }
            }

            current.groups
                .filter { recycleBinUuid == null || it.uuid.compareTo(recycleBinUuid) != 0 }
                .forEach { stack.push(it to searchEnabled) }
        }

        return null
    }

    fun findChildEntries(
        useGroupOverride: Boolean = false,
        recycleBinUuid: Uuid? = null,
        predicate: (Entry) -> Boolean,
    ): List<Pair<Group, List<Entry>>> {
        val result = mutableListOf<Pair<Group, List<Entry>>>()
        val stack = Stack<Pair<Group, Boolean>>()
        stack.push(this to true)

        while (!stack.isEmpty) {
            val (current, parentSearchEnabled) = stack.pop()!!
            val searchEnabled = when (current.enableSearching) {
                GroupOverride.Inherit -> parentSearchEnabled
                GroupOverride.Enabled -> true
                GroupOverride.Disabled -> false
            }
            if (!useGroupOverride || searchEnabled) {
                val found = current.entries.filter { predicate(it) }

                if (found.isNotEmpty()) {
                    result.add(current to found)
                }
            }

            current.groups
                .filter { recycleBinUuid == null || it.uuid.compareTo(recycleBinUuid) != 0 }
                .forEach { stack.push(it to searchEnabled) }
        }

        return result
    }

    companion object {
        /**
         * Creates [Group] with proper settings for Recycle Bin.
         */
        fun createRecycleBin(name: String) = Group(
            uuid = uuid4(),
            name = name,
            icon = PredefinedIcon.TrashBin,
            enableSearching = GroupOverride.Disabled,
            enableAutoType = GroupOverride.Disabled,
        )
    }
}


enum class GroupOverride {
    Inherit,
    Enabled,
    Disabled
}
