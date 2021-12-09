package listners

import error.DimensionPluginException
import error.EmptyException
import error.WrongHeightException
import error.WrongWidthException
import model.DimensionModel
import util.MessageDelegate
import view.GeneratorView
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class GenerateActionListener(
    private val generatorView: GeneratorView,
    private val eventListener: GuiFormEventListener,
    private val messageDelegate: MessageDelegate
) : ActionListener {

    override fun actionPerformed(actionEvent: ActionEvent) {
        with(generatorView) {
            try {
                checkFormValidation(generatorView)
                eventListener.onViewPortObtained(getGeneratorModel(generatorView))
            } catch (exception: DimensionPluginException) {
                messageDelegate.onPluginExceptionHandled(exception)
            }
        }
    }

    private fun checkFormValidation(generatorView: GeneratorView) {
        if (generatorView.viewPortWidth.text.isNullOrEmpty()) {
            throw  EmptyException("width")
        }
        if (
            !generatorView.viewPortWidth.text.matches(VIEW_PORT_PATTERN) ||
            !(generatorView.viewPortWidth.text.length == 3 || generatorView.viewPortWidth.text.length == 4)
        ) {
            throw WrongWidthException()
        }

        if (generatorView.viewPortHeight.text.isNullOrEmpty()) {
            throw  EmptyException("height")
        }
        if (!generatorView.viewPortHeight.text.matches(VIEW_PORT_PATTERN) ||
            !(generatorView.viewPortHeight.text.length == 3 || generatorView.viewPortHeight.text.length == 4)
        ) {
            throw WrongHeightException()
        }
    }

    private fun getGeneratorModel(generatorView: GeneratorView): DimensionModel =
        with(generatorView) {
            DimensionModel(
                height = generatorView.viewPortHeight.text.toDouble(),
                width = generatorView.viewPortWidth.text.toDouble()
            )
        }

    private val VIEW_PORT_PATTERN = "^[0-9]*$".toRegex()
}
