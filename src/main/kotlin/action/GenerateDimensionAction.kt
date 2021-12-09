package action

import PluginApplication
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.Presentation


class GenerateDimensionAction : AnAction() {

    private val pluginApplication = PluginApplication()

    override fun actionPerformed(e: AnActionEvent) {
        pluginApplication.actionPerformed(e)
    }

    override fun update(e: AnActionEvent) {
        val presentation = e.presentation
        val project = e.project

        if (project == null) {
            disable(presentation)
            return
        }
        val file = e.getData(CommonDataKeys.NAVIGATABLE)
        if (file == null) {
            disable(presentation)
            return
        }

        if (file.toString().substringAfterLast("/") != "res") {
//        if (!file.toString().contains("/res")) {
            disable(presentation)
            return
        }

        enable(presentation)
    }

    private fun disable(presentation: Presentation) {
        presentation.isEnabledAndVisible = false
    }

    private fun enable(presentation: Presentation) {
        presentation.isEnabledAndVisible = true
    }

}