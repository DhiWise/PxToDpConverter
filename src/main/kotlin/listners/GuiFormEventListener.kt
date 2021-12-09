package listners

import model.DimensionModel


interface GuiFormEventListener {
    fun onViewPortObtained(model: DimensionModel)
}
