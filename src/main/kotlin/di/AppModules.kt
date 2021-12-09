package di

import controller.DimensionActionController
import delegates.EnvironmentDelegate
import delegates.GenerationDelegate
import generator.DimensionCreator
import org.koin.dsl.module
import util.MessageDelegate
import view.GeneratorViewFactory

val appModule = module {

    single {
        DimensionActionController(get(), get(), get(), get())
    }
    single {
        EnvironmentDelegate()
    }

    single {
        MessageDelegate()
    }

    single {
        GeneratorViewFactory(get())
    }

    single {
        GenerationDelegate(get(), get(),get())
    }

    single {
        DimensionCreator()
    }


}