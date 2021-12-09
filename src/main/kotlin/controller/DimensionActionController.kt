package controller

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogBuilder
import delegates.EnvironmentDelegate
import delegates.GenerationDelegate
import error.DimensionPluginException
import listners.GuiFormEventListener
import model.DimensionModel
import util.MessageDelegate
import view.GeneratorViewFactory

class DimensionActionController(
    private val environmentDelegate: EnvironmentDelegate,
    private val messageDelegate: MessageDelegate,
    private val generatorViewFactory: GeneratorViewFactory,
    private val generationDelegate: GenerationDelegate
) {

    fun onActionHandled(event: AnActionEvent) {
        try {
            proceed(event)
        } catch (exception: DimensionPluginException) {
            messageDelegate.onPluginExceptionHandled(exception)
        }
    }

    private fun proceed(event: AnActionEvent) {
        val projectModel = environmentDelegate.obtainProjectModel(event)
        val dialogBuilder = DialogBuilder()
        val window = dialogBuilder.window
        generatorViewFactory.bindView(dialogBuilder, object : GuiFormEventListener {
            override fun onViewPortObtained(model: DimensionModel) {
                window.dispose()
                generationDelegate.runGenerationTask(projectModel,model)
            }
        })
    }
}