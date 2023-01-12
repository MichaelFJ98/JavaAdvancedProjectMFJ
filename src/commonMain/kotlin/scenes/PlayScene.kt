package scenes

import com.soywiz.klock.*
import com.soywiz.korev.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import components.*
import kotlin.random.*

class PlayScene : Scene() {
    //Variables
    private val groundLevel:Double = 600.0 //Y-value of ground level
    private val playerStartPosition = 100.0 //starting X-value of player
    private var unlockBoost = Random.nextDouble(1000.0,1500.0)


    private val startingPos1 = 1000.0
    private val startingPos2 = 1300.0
    private val startingPos3 = 1750.0
    private val startingPos4 = 2100.0

    //components
    private lateinit var player: Player
    private lateinit var ground: Ground
    private lateinit var score: Score
    private lateinit var circles: Circles
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
        circles = Circles()
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
        while (true) {
            delay(TimeSpan(1.0))

            if (player.state == Player.State.MINIGAME) {
                circles.update()
                if (input.keys[Key.A] && circles.state == Circles.State.ALIVE) {
                    player.state = Player.State.BOOSTED
                    removeChild(circles.getViewInner())
                }
                if (circles.state == Circles.State.DEAD) {
                    removeChild(circles.getViewInner())
                    player.state = Player.State.ALIVE
                }
            } else {

                if (player.state != Player.State.DEAD) {
                    player.update()
                    score.increase(this)

                    if (input.keys[Key.SPACE] && player.y >= ((groundLevel - player.defaultHeight) - 1)) {
                        player.jump()
                    }

                    if (score.getScore() > unlockBoost && player.y >= ((groundLevel - player.defaultHeight) - 1)) {
                        circles.init(this)
                        player.state = Player.State.MINIGAME
                        unlockBoost = Random.nextDouble((unlockBoost + 2000) , (unlockBoost + 5000.0))
                    }

                    if (player.state == Player.State.BOOSTED && score.getScore() > (player.startScore + 2000.0)) {
                        player.state = Player.State.ALIVE
                    }

                    for (spike in spikes) {
                        spike.update()

                        if (player.drawModel.collidesWith(spike.getView())) {
                            player.die()
                        }

                        if (spike.getView().x < -100.0) {
                            spike.getView().x += 1400.0
                        }
                    }
                } else {
                    sceneContainer.changeTo<Scene>({ MenuScene() })
                }
            }
        }
    }

}
