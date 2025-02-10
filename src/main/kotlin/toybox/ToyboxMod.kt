package toybox

import arc.Events
import arc.util.Log
import arc.util.Time
import mindustry.game.EventType.ClientLoadEvent
import mindustry.mod.Mod
import toybox.content.oilprocessing.ToyboxOilProcessingLiquids
import toybox.content.oilprocessing.blocks.ToyboxOilProcessingBlocks

class ToyboxMod : Mod() {

    init {
        Log.info("Loaded toybox constructor.")

        // listen for game load event
        Events.on(ClientLoadEvent::class.java) {
            // show dialog upon startup
            Time.runTask(10f) {
                Log.info("hello wlorld")
            }
        }
    }

    override fun loadContent() {

        ToyboxOilProcessingLiquids.load()
        ToyboxOilProcessingBlocks.load()
    }
}