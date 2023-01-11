package scenes

import com.soywiz.korev.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import components.*


class PlayScene : Scene() {
    //Variables
    private val groundLevel:Double = 600.0 //Y-value of ground level
    private val playerStartPosition = 100.0
    private val startingPos1 = 1000.0
    private val startingPos2 = 1300.0
    private val startingPos3 = 1750.0
    private val startingPos4 = 2100.0

    //components
    private lateinit var player: Player
    private lateinit var ground: Ground
    private lateinit var spike1: SingleSpike
    private lateinit var spike2: SingleSpike
    private lateinit var spike3: MultiSpike
    private lateinit var spike4: SingleSpike
    private lateinit var spikes: MutableList<Spike>


    override suspend fun SContainer.sceneMain() {

        //Sprites
        val backgroundSprite: Bitmap = resourcesVfs["background.jpg"].readBitmap()

        //components
        player = Player(playerStartPosition, groundLevel)
        ground = Ground()
        spike1  = SingleSpike(startingPos1)
        spike2 = SingleSpike(startingPos2)
        spike3 = MultiSpike(startingPos3)
        spike4 = SingleSpike(startingPos4)
        spikes = mutableListOf(spike1, spike2, spike3, spike4)


        //draw components
        player.draw(this)
        ground.draw(this)
        for(spike in spikes){
            spike.draw(this)
        }

        //gameloop
        addUpdater {
            if(player.state !== Player.State.DEAD) {
                player.update()

                if (input.keys[Key.SPACE] && player.y >= ((groundLevel - player.defaultHeight) - 1)) {
                    player.jump()
                }

                for (spike in spikes) {

                    spike.update()

                    if (player.drawModel.collidesWith(spike.getView()) && Player.State.BOOSTED == player.state) {
                        player.die()
                    }
                }
            }
            else{

                println("tis gedoan")
            }
        }

    }

}
