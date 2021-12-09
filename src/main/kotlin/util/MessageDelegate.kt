package util

import com.intellij.notification.Notification
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.Messages
import error.DimensionPluginException


class MessageDelegate {

    fun onPluginExceptionHandled(exception: DimensionPluginException) {
        showMessage(exception.message, exception.header)
    }

    fun logEventMessage(message: String) {
        sendNotification(message, NotificationType.WARNING)
    }

    fun showSuccessMessage() {
        sendNotification(TITLE_SUCCESS, NotificationType.INFORMATION)
    }

    private fun sendNotification(message: String, type: NotificationType) {
        ApplicationManager.getApplication().invokeLater {
             showMessage(message, type, ProjectManager.getInstance().openProjects[0])
        }
    }


    private fun showMessage(message: String, type: NotificationType, project: Project) {
        NotificationGroupManager.getInstance().getNotificationGroup(GROUP_ID)
            .createNotification(message, type)
            .notify(project)
    }

    private fun showMessage(message: String?, header: String) {
        Messages.showDialog(message, header, arrayOf(TITLE_OK), -1, null)
    }
}

private const val TITLE_OK = "OK"
private const val TITLE_SUCCESS = "Dimensions generated successfully"
 const val GROUP_ID = "PxToDp Converter"
