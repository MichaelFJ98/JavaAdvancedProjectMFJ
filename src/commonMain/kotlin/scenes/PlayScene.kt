package scenes

import com.soywiz.klock.*
import com.soywiz.korev.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import components.*


class PlayScene : Scene() {
    //Variables
    private val groundLevel:Double = 600.0 //Y-value of ground level
    private val playerStartPosition = 100.0 //starting X-value of player

    private val startingPos1 = 1000.0
    private val startingPos2 = 1300.0
    private val startingPos3 = 1750.0
    private val startingPos4 = 2100.0

    //components
    private lateinit var player: Player
    private lateinit var ground: Ground
    private lateinit var score: Score
    private lateinit var spike1: SingleSpike
    private lateinit var spike2: SingleSpike
    private lateinit var spike3: MultiSpike
    private lateinit var spike4: SingleSpike
    private lateinit var spikes: MutableList<Spike>

    override suspend fun SContainer.sceneMain() {

        //components
        player = Player(playerStartPosition, groundLevel)
        ground = Ground()
        score = Score()
        spike1  = SingleSpike(startingPos1)
        spike2 = SingleSpike(startingPos2)
        spike3 = MultiSpike(startingPos3)
        spike4 = SingleSpike(startingPos4)
        spikes = mutableListOf(spike1, spike2, spike3, spike4)


        //draw components
        player.draw(this)
        ground.draw(this)
        score.addUI(this)
        for(spike in spikes){
            spike.draw(this)
        }

        //gameloop
        addUpdater {
            if(player.state !== Player.State.DEAD) {
                player.update()
                score.increase(this)

                if (input.keys[Key.SPACE] && player.y >= ((groundLevel - player.defaultHeight) - 1)) {
                    player.jump()
                }

                for (spike in spikes) {
                    spike.update()

                    if (player.drawModel.collidesWith(spike.getView()) && Player.State.ALIVE == player.state) {
                        player.die()
                    }
                }
            }
            else{
                println("dead")
            }
        }


    }

}
