package delegates

import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFileManager
import model.ProjectModel
import util.createFilePath

class EnvironmentDelegate {

    fun obtainProjectModel(event: AnActionEvent): ProjectModel {
        val project = event.project as Project
        return ProjectModel(
            project = project,
            resFolder =
                project.basePath?.let {
                    createFilePath(
                        it,
                        "app",
                        "src",
                        "main",
                        "res",
                        "values"
                    )
                }


        )
    }

    fun refreshProject(projectModel: ProjectModel) {
        ProjectView.getInstance(projectModel.project).refresh()
        VirtualFileManager.getInstance().refreshWithoutFileWatcher(true)
    }


}
