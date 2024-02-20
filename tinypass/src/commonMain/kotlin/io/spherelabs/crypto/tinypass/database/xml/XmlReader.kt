package io.spherelabs.crypto.tinypass.database.xml

import com.fleeksoft.ksoup.nodes.Element

interface XmlReader<T : Any> {
    fun read(element: Element): T
}
