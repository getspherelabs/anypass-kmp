package io.spherelabs.crypto.kdbx.database.model.autotype

import io.spherelabs.crypto.kdbx.database.FormatXml


internal fun Boolean.toXmlString() = if (this) {
    FormatXml.Values.True
} else {
    FormatXml.Values.False
}
