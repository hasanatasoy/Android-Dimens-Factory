package Logic

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class XmlOperations{

    private lateinit var document: Document
    private val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    private val documentBuilder = documentBuilderFactory.newDocumentBuilder()
    private val tranformFactory = TransformerFactory.newInstance()

    fun createXmlCodeAndReturnRootElementAndDocument(): Triple<Document,Element,String>{
        document = documentBuilder.newDocument()
        var root = document.createElement("resource")
        document.appendChild(root)
        root.setAttribute("xmlns:tools", "http://schemas.android.com/tools")
        return Triple(document,root,"Success")
    }
    fun parseXmlAndReturnElementList(xmlPath:String,elementTag:String):NodeList{
        document = documentBuilder.parse(xmlPath)
        return document.getElementsByTagName(elementTag)
    }
    fun createAnElementWithNameAndAttribute(document:Document,
                                            elementTag: String,
                                            elementDetails:Triple<String,String,String>):Element{
        var element = document.createElement(elementTag)
        element.setAttribute(elementDetails.first,elementDetails.second)
        element.appendChild(document.createTextNode(elementDetails.third))
        return element
    }
    fun changeDımensOneByOneWithFactoryAndSetOtherXml(factory:Double, rootDocument:Document,
                                                      rootElement:Element, willChangeElementList:NodeList):Document{
        println("Factory => $factory  >>"+factory+2)
        for (count in 0 until willChangeElementList.length){
            var element = willChangeElementList.item(count) as Element
            var attributeValue = element.getAttribute("name")
            var elementText = element.textContent
            var stringBuilder = StringBuilder(elementText)
            stringBuilder.deleteCharAt(elementText.length-1)
            var intElementText = stringBuilder.toString().trim()
            stringBuilder = java.lang.StringBuilder(intElementText)
            stringBuilder.deleteCharAt(intElementText.length-1)
            intElementText = stringBuilder.toString().trim()
            var intText = intElementText.toInt()
            var newIntText = intText*factory
            var newElement = createAnElementWithNameAndAttribute(rootDocument,"dimen",Triple("name",
                                                                                        attributeValue,
                                                                                        ""+newIntText+"dp"))
            rootElement.appendChild(newElement)
            println("newIntText >>>>> $newIntText")
        }
        return rootDocument
    }
    fun createXmlFileAndSetupInXmlCode(document:Document,targetFilePath:String,dimensType:String){
        var transformer = tranformFactory.newTransformer()
        var source = DOMSource(document)
        var result = StreamResult(File("$targetFilePath/dimens-$dimensType.xml"))
        transformer.transform(source, result)
        println("File created succesfully")
    }
}