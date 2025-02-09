package toybox.content

import arc.graphics.Color
import arc.graphics.Colors
import arc.struct.ObjectSet
import mindustry.content.Liquids
import mindustry.type.Liquid

class ToyboxLiquids {
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