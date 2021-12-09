package generator

import model.DimensionModel
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import util.*
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

object XmlGenerator {
    /**
     *@return Its return Document instance
     * */
     fun getXMLDoc(outputXML: File): Document {
        if (outputXML.exists()) {
            val text = outputXML.readText()
            if (text.trim().isNotBlank()) {
                try {
                    return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(outputXML)
                } catch (e: Exception) {
                    throw Exception("XML File not formatted correctly. Exception : ${e.message}")
                }
            }
        }
        val doc: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
        doc.xmlStandalone = true
        return doc
    }


    /**
     *@param document is contain all attribute of screen
     * @param filePath is location of xml file
     * @param fileName is xml file
     *
     * Its generate xml file from xml attribute
     * */
     fun generateXMLFile(document: Document, filePath: String, fileName: String) {
        var dir = File(filePath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        var file = File(filePath, fileName)
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
        val transformerFactory = TransformerFactory.newInstance()
        transformerFactory.setAttribute("indent-number", 2)
        val transformer = transformerFactory.newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(DOMSource(document), StreamResult(File(filePath, fileName)))
    }


}