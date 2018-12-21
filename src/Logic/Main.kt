package Logic

import ViewHolder.ViewHolder
import javax.swing.*

class Main {

    fun setupView():ViewHolder{
        var view = ViewHolder()
        var jFrame = JFrame()
        jFrame.setBounds(0,0,500,400)
        jFrame.contentPane = view.mainPanel
        jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jFrame.isVisible = true
        jFrame.isResizable = false
        return view
    }

    companion object {
        @JvmStatic
        fun main(args : Array<String>) {
            var view = Main()
            var viewHolder = view.setupView()
            val xmlOperations = XmlOperations()
            viewHolder.createDimensFileButton!!.addActionListener {
                var existElementList =
                    xmlOperations.parseXmlAndReturnElementList(viewHolder.existDimensPathWithTextField!!.text,"dimen")
                //320
                var documentAndElement320 = xmlOperations.createXmlCodeAndReturnRootElementAndDocument()
                var document320 = documentAndElement320.first
                var element320 = documentAndElement320.second
                var newDocument320 = xmlOperations.
                    changeDımensOneByOneWithFactoryAndSetOtherXml(viewHolder.factory320dpText!!.text.toDouble(),
                        document320,
                        element320,
                        existElementList)
                xmlOperations.
                    createXmlFileAndSetupInXmlCode(newDocument320,viewHolder.targetDimensPath!!.text, "320")
                //480
                var documentAndElement480 = xmlOperations.createXmlCodeAndReturnRootElementAndDocument()
                var document480 = documentAndElement480.first
                var element480 = documentAndElement480.second
                var newDocument480 = xmlOperations.
                    changeDımensOneByOneWithFactoryAndSetOtherXml(viewHolder.factory480dpText!!.text.toDouble(),
                        document480,
                        element480,
                        existElementList)
                xmlOperations.createXmlFileAndSetupInXmlCode(newDocument480,viewHolder.targetDimensPath!!.text, "480")
                //600
                var documentAndElement600 = xmlOperations.createXmlCodeAndReturnRootElementAndDocument()
                var document600 = documentAndElement600.first
                var element600 = documentAndElement600.second
                var newDocument600 = xmlOperations.
                    changeDımensOneByOneWithFactoryAndSetOtherXml(viewHolder.factory600dpText!!.text.toDouble(),
                        document600,
                        element600,
                        existElementList)
                xmlOperations.createXmlFileAndSetupInXmlCode(newDocument600,viewHolder.targetDimensPath!!.text, "600")
                //720
                var documentAndElement720 = xmlOperations.createXmlCodeAndReturnRootElementAndDocument()
                var document720 = documentAndElement720.first
                var element720 = documentAndElement720.second
                var newDocument720 = xmlOperations.
                    changeDımensOneByOneWithFactoryAndSetOtherXml(viewHolder.factory720dpText!!.text.toDouble(),
                        document720,
                        element720,
                        existElementList)
                xmlOperations.createXmlFileAndSetupInXmlCode(newDocument720,viewHolder.targetDimensPath!!.text, "720")
            }
        }
    }
}


