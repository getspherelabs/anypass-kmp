package io.spherelabs.crypto.tinypass.database.xml

import io.spherelabs.crypto.tinypass.database.model.component.CustomDataValue
import io.spherelabs.crypto.tinypass.database.model.component.KdbxQuery
import io.spherelabs.crypto.tinypass.database.model.component.TimeData

sealed interface XmlComponent {
    data class Group(val group: io.spherelabs.crypto.tinypass.database.model.component.Group) :
        XmlComponent

    data class Time(val timeDatta: TimeData) : XmlComponent
    data class Meta(val meta: io.spherelabs.crypto.tinypass.database.model.component.Meta) :
        XmlComponent

    data class CustomData(val data: Map<String, CustomDataValue>): XmlComponent
    data class Content(val query: KdbxQuery): XmlComponent
}
