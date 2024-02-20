package io.spherelabs.crypto.tinypass.database.core

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.ported.BufferReader
import io.spherelabs.crypto.tinypass.database.FormatXml
import io.spherelabs.crypto.tinypass.database.common.addBytes
import io.spherelabs.crypto.tinypass.database.common.addUuid
import io.spherelabs.crypto.tinypass.database.common.deserialize
import io.spherelabs.crypto.tinypass.database.model.autotype.toXmlString
import io.spherelabs.crypto.tinypass.database.model.component.*
import io.spherelabs.crypto.tinypass.database.model.component.CustomIcons
import okio.BufferedSource


//object XmlContentParser {
//    private const val encoding = "utf-8"
//
//    fun deserialize(bytes: ByteArray, block: (Meta) -> XmlContext.Decode) {
//        TODO()
//    }
//
//    fun deserialize(source: BufferedSource, block: (Meta) -> XmlContext.Decode): DatabaseContent {
//        val document = Ksoup.parse(bufferReader = BufferReader(source), baseUri = "", encoding)
//        val root = document.root()
//        val meta = document.select("meta").first()?.let { element ->
//            Meta.deserialize(element)
//        }
//        val group = document.select("group").first()?.let {
//
//        }
//
//    }
//
//}
