package io.spherelabs.crypto.tinypass.database.common

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.nodes.XmlDeclaration
import com.fleeksoft.ksoup.parser.Parser
import com.fleeksoft.ksoup.ported.BufferReader
import okio.BufferedSource

private const val KEY_FILE = "keyfile"
private const val XML = "xml"
private const val VERSION = "version"
private const val ENCODING = "encoding"
private const val EMPTY_URI = ""

fun xml(
    version: String,
    charset: String,
    block: (Element.() -> Element),
): Document {
    return Document(EMPTY_URI).apply {
        appendElement(KEY_FILE).apply {
            block.invoke(this)
        }
        outputSettings().syntax(Document.OutputSettings.Syntax.xml)
        val xmlDeclaration = XmlDeclaration(XML, false)
        xmlDeclaration.attr(VERSION, version)
        xmlDeclaration.attr(ENCODING, charset)
        prependChild(xmlDeclaration)
    }
}

fun xmlParser(
    content: String,
): Document {
    return Parser.xmlParser().parseInput(html = content, EMPTY_URI)
}

fun xmlParser(
    content: ByteArray,
): Document {

    return Parser.xmlParser().parseInput(htmlBytes = content, EMPTY_URI)
}

fun xmlParser(
    source: BufferedSource,
): Document {
    return Parser.xmlParser().parseInput(inputHtml = BufferReader(source), EMPTY_URI)
}

