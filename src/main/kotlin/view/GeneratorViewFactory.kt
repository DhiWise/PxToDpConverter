package view

import com.intellij.openapi.ui.DialogBuilder
import listners.GenerateActionListener
import listners.GuiFormEventListener
import util.MessageDelegate

class GeneratorViewFactory(
    private val messageDelegate: MessageDelegate
) {

    fun bindView(builder: DialogBuilder, eventListener: GuiFormEventListener) {
        val generatorView = GeneratorView()
        val actionListener = GenerateActionListener(
            generatorView = generatorView,
            eventListener = eventListener,
            messageDelegate = messageDelegate
        )
        with(generatorView) {
            generateButton.addActionListener(actionListener)
            builder.setCenterPanel(rootView)
        }
        builder.apply {
            setTitle(PLUGIN_TITLE)
            removeAllActions()
            show()
        }
    }
}

private const val PLUGIN_TITLE = "PxToDp Converter"
