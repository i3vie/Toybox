package toybox.content.oilprocessing.blocks

import arc.util.Log
import mindustry.content.Items
import mindustry.content.Liquids
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.type.LiquidStack
import mindustry.world.blocks.production.GenericCrafter
import toybox.content.oilprocessing.ToyboxOilProcessingLiquids

class ToyboxOilProcessingBlocks {
    companion object {
        lateinit var distillationColumn: GenericCrafter

        fun load() {
            distillationColumn = object : GenericCrafter("distillation-column") {
                init {
                    Log.info("Distillation column init was hit")
                    requirements(Category.crafting, arrayOf(
                        ItemStack(Items.copper, 400),
                        ItemStack(Items.titanium, 300),
                        ItemStack(Items.lead, 250)
                    ))

                    craftTime = 90f // 1.5 seconds
                    size = 3

                    // Floats like 0.2f are how many each tick
                    // 60 ticks in a second

                    consumesPower = true
                    consumeLiquid(Liquids.oil, 0.5f) // 0.5 * 60 = 30 oil/sec
                    consumePower(2.5f)               // 2.5 * 60 = 150 power/sec

                    outputsLiquid = true
                    outputLiquids = arrayOf(
                        LiquidStack(ToyboxOilProcessingLiquids.gasoline, 0.075f),
                        LiquidStack(Liquids.water, 0.35f) // TODO(i3vie): Naphtha
                    )

                    liquidOutputDirections = intArrayOf(2, 0)

                }
            }
        }
    }
}
