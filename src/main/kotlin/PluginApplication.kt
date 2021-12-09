import com.intellij.openapi.actionSystem.AnActionEvent
import controller.DimensionActionController
import di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class PluginApplication : KoinComponent {
    init {
        GlobalContext.getOrNull() ?: startKoin { modules(appModule) }
    }

    private val controller: DimensionActionController by inject()

    fun actionPerformed(actionEvent: AnActionEvent) {
        controller.onActionHandled(actionEvent)
    }
}
