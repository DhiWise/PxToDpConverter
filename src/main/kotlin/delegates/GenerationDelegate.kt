package delegates

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task.Backgroundable
import error.DimensionPluginException
import generator.DimensionCreator
import model.DimensionModel
import model.ProjectModel
import util.MessageDelegate

class GenerationDelegate(
        private val environmentDelegate: EnvironmentDelegate,
        private val messageDelegate: MessageDelegate,
        private val dimensionCreator: DimensionCreator
) {

    fun runGenerationTask(
        projectModel: ProjectModel,
        model: DimensionModel
    ) {
        ProgressManager.getInstance().run(object : Backgroundable(projectModel.project,
                TASK_TITLE, false) {
            override fun run(indicator: ProgressIndicator) {
                try {
                    projectModel.resFolder?.let { dimensionCreator.writeDimensionFile(model, it) }
                    messageDelegate.showSuccessMessage()
                } catch (e: DimensionPluginException) {
                    messageDelegate.onPluginExceptionHandled(e)
                } finally {
                    indicator.stop()
                    environmentDelegate.refreshProject(projectModel)
                }
            }
        })
    }
}

private const val TASK_TITLE = "PxToDp Converter"
