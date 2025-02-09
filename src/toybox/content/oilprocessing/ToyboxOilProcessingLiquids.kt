package toybox.content.oilprocessing

import arc.graphics.Color
import mindustry.type.Liquid

class ToyboxOilProcessingLiquids {
    companion object {
        lateinit var gasoline: Liquid

        fun load() {
            gasoline = object : Liquid("gasoline") {
                init {
                    color = Color.rgb(224, 221, 130)
                    flammability = 1f
                    heatCapacity = 0.25f
                    viscosity = 0.35f
                    coolant = false
                }
            }
        }
    }
}