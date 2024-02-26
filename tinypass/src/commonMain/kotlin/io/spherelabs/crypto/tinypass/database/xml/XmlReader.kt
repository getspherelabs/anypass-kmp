package io.spherelabs.crypto.tinypass.database.xml

import com.fleeksoft.ksoup.nodes.Element
import io.spherelabs.crypto.tinypass.database.common.xmlParser
import io.spherelabs.crypto.tinypass.database.core.KdbxDatabase
import io.spherelabs.crypto.tinypass.database.core.KdbxQuery
import okio.BufferedSource


object XmlReader {
    fun read(source: BufferedSource, option: XmlOption): KdbxQuery {
        val documentation = xmlParser(source.readByteArray())
        val root = documentation.root()
        TODO()
    }
}
