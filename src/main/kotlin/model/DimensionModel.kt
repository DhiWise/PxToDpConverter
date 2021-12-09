package model

import com.intellij.openapi.project.Project

data class DimensionModel(val width: Double, val height: Double)
data class ProjectModel(
    val project: Project,
    val resFolder: String?
)
