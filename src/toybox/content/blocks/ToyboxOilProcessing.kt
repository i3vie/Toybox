package toybox.content.blocks

import mindustry.content.Items
import mindustry.content.Liquids
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.type.LiquidStack
import mindustry.world.Block
import mindustry.world.blocks.production.GenericCrafter
import toybox.content.ToyboxLiquids

class ToyboxOilProcessing {
    // Distillation column
    lateinit var distillationColumn: GenericCrafter

    fun load() {
        distillationColumn = object : GenericCrafter("distillation-column") {
            init {
                category = Category.crafting
                requirements = arrayOf(
                    ItemStack(Items.lead, 250),
                    ItemStack(Items.titanium, 300),
                    ItemStack(Items.copper, 400)
                )

                craftTime = 90f
                size = 3

                consumeLiquid(Liquids.oil, 0.2f)
                consumePower(2.0f)

                // TODO(i3vie): figure out how to get gasoline here for outputLiquids

            }
        }
    }
}
