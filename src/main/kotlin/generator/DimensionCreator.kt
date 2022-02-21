package generator

import model.DimensionModel
import org.w3c.dom.NodeList
import util.*
import java.io.File
import java.util.*

class DimensionCreator {

    fun writeDimensionFile(model: DimensionModel, outputFilePath: String) {

        generateXMLFile(model, outputFilePath)
        generateHeightFiles(model, outputFilePath)

        DIMENSION_WIDTH_LIST.forEach { singleDimen ->
            generateXMLFile(model, outputFilePath, singleDimen)
        }
        DIMENSION_HEIGHT_LIST.forEach { singleDimen ->
            generateHeightFiles(model, outputFilePath, singleDimen)
        }

    }

    private fun generateXMLFile(model: DimensionModel, outputFilePath: String, baseFileIdentifier: Int = 0) {

        val dimensionOutputPath = if (baseFileIdentifier == 0) {
            outputFilePath
        } else {
            outputFilePath + "-sw" + baseFileIdentifier.toString() + "dp"
        }


        val dimensionXML = File(
            dimensionOutputPath,
            XML_FILE_NAME.lowercase(Locale.getDefault()) + XML_FILE_EXTENSION
        )
        val document = XmlGenerator.getXMLDoc(dimensionXML)

        val baseDimension = if (model.width != 0.0) {
            model.width
        } else {
            DIMENSION_WIDTH
        }

        var isRootDefined = false
        val rootElement =
            if (dimensionXML.exists()) {
                val rootExists = document.getElementsByTagName(XML_ROOT_ELEMENT_NAME).item(0)
                if (rootExists == null) {
                    isRootDefined = false
                    document.createElement(
                        XML_ROOT_ELEMENT_NAME
                    )
                } else {
                    isRootDefined = true
                    rootExists
                }
            } else {
                document.createElement(XML_ROOT_ELEMENT_NAME)
            }

        val ele = document.getElementsByTagName(XML_ELEMENT_NAME)

        val duplicateKeys = mutableListOf<String>()

        DIMENSION_RANGE_WIDTH.forEach { dimenKey ->
            val attrValue = "_" + dimenKey + "pxh"

            val keyExisted = checkIfKeyExists(ele, XML_ATTRIBUTE_NAME, attrValue)

            if (!keyExisted) {
                val stringElement = document.createElement(XML_ELEMENT_NAME)

                stringElement.setAttribute(XML_ATTRIBUTE_NAME, attrValue)

                stringElement.textContent = if (baseFileIdentifier == 0) {
                    (dimenKey).toDouble().roundTo(DIGITS_AFTER_DECIMAL).toString() + "dp"
                } else {
                    (dimenKey * (baseFileIdentifier / baseDimension)).roundTo(DIGITS_AFTER_DECIMAL).toString() + "dp"
                }

                rootElement.appendChild(stringElement)
            } else {
                duplicateKeys.add(attrValue)
            }
        }
        if (!dimensionXML.exists() || !isRootDefined) {
            document.appendChild(rootElement)
        }
        XmlGenerator.generateXMLFile(
            document,
            dimensionOutputPath,
            XML_FILE_NAME.lowercase(Locale.getDefault()) + XML_FILE_EXTENSION
        )
    }

    private fun generateHeightFiles(model: DimensionModel, outputFilePath: String, baseFileIdentifier: Int = 0) {

        val dimensionOutputPath = if (baseFileIdentifier == 0) {
            outputFilePath
        } else {
            (outputFilePath + "-h" + baseFileIdentifier.toString() + "dp")
        }

        val dimensionXML = File(
            dimensionOutputPath,
            XML_FILE_NAME.lowercase(Locale.getDefault()) + XML_FILE_EXTENSION
        )
        val document = XmlGenerator.getXMLDoc(dimensionXML)

        val figmaWidth = if (model.width != 0.0) {
            model.width
        } else {
            DIMENSION_WIDTH
        }
        val figmaHeight = figmaWidth * 2.16
        val baseDimension = if (model.height != 0.0) {
            model.height
        } else {
            figmaHeight
        }

        var isRootDefined = false
        val rootElement =
            if (dimensionXML.exists()) {
                val rootExists = document.getElementsByTagName(XML_ROOT_ELEMENT_NAME).item(0)
                if (rootExists == null) {
                    isRootDefined = false
                    document.createElement(
                        XML_ROOT_ELEMENT_NAME
                    )
                } else {
                    isRootDefined = true
                    rootExists
                }
            } else {
                document.createElement(XML_ROOT_ELEMENT_NAME)
            }

        val ele = document.getElementsByTagName(XML_ELEMENT_NAME)

        val duplicateKeys = mutableListOf<String>()

        DIMENSION_RANGE_HEIGHT.forEach { dimenKey ->
            val attrValue = "_" + dimenKey + "pxv"

            val keyExisted = checkIfKeyExists(ele, XML_ATTRIBUTE_NAME, attrValue)

            if (!keyExisted) {
                val stringElement = document.createElement(XML_ELEMENT_NAME)

                stringElement.setAttribute(XML_ATTRIBUTE_NAME, attrValue)

                stringElement.textContent = if (baseFileIdentifier == 0) {
                    (dimenKey).toDouble().roundTo(DIGITS_AFTER_DECIMAL).toString() + "dp"
                } else {
                    (dimenKey * (baseFileIdentifier / baseDimension)).roundTo(DIGITS_AFTER_DECIMAL).toString() + "dp"
                }

                rootElement.appendChild(stringElement)
            } else {
                duplicateKeys.add(attrValue)
            }
        }
        if (!dimensionXML.exists() || !isRootDefined) {
            document.appendChild(rootElement)
        }

        XmlGenerator.generateXMLFile(
            document,
            dimensionOutputPath,
            XML_FILE_NAME.lowercase(Locale.getDefault()) + XML_FILE_EXTENSION
        )
    }

    /**
     * Check if key with same name is already exists...
     * @param elements : Available list of elements
     * @param key : key of attribute
     * @param valueOfKey : value of attribute ${XML_ATTRIBUTE_NAME} inside element
     */
    private fun checkIfKeyExists(elements: NodeList?, key: String, valueOfKey: String): Boolean {
        for (index in 0 until (elements?.length ?: 0)) {
            if (valueOfKey == elements?.item(index)?.attributes?.getNamedItem(key)?.nodeValue) return true
        }
        return false
    }
}