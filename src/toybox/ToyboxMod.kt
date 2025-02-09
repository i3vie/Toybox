package toybox

import arc.*
import arc.util.*
import mindustry.game.EventType.*
import mindustry.mod.*
import toybox.content.oilprocessing.ToyboxOilProcessingLiquids
import toybox.content.oilprocessing.blocks.ToyboxOilProcessingBlocks

class ToyboxMod : Mod(){

    init{
        Log.info("Loaded toybox constructor.")

        //listen for game load event
        Events.on(ClientLoadEvent::class.java){
            //show dialog upon startup
            Time.runTask(10f){
                Log.info("hello wlorld")
            }
        }
    }

    override fun loadContent(){

        ToyboxOilProcessingLiquids.load()
        ToyboxOilProcessingBlocks.load()
    }
}
