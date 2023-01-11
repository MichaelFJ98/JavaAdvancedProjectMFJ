package scenes

import com.soywiz.korev.*
import com.soywiz.korge.animate.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import org.jbox2d.common.*
import org.jbox2d.dynamics.*
import com.soywiz.korma.geom.Angle
import components.*


class PlayScene : Scene() {
    //Variables
    val groundLevel:Double = 600.0 //Y-value of ground level
    val jumpHeight:Double = 50.0 // # pixels you jump
    val playerStartPosition = 100.0

    //components
    private lateinit var player: Player
    private lateinit var ground: Ground
    private lateinit var spike1: Spike


    override suspend fun SContainer.sceneMain() {

        //Sprites
        val backgroundSprite: Bitmap = resourcesVfs["background.jpg"].readBitmap()

        //components
        player = Player(playerStartPosition, groundLevel)
        ground = Ground()
        spike1  = Spike()


        //draw components
        player.draw(this)
        ground.draw(this)
        spike1.drawSingular(this)

        //gameloop
        addUpdater {
            println("playerobject: ${player.y}")
            println("singlespike: ${spike1.drawSingularSpike.x}")
            player.update()
            spike1.updateSingular()

            if (input.keys[Key.SPACE] && player.y >= ((groundLevel - player.defaultHeight)- 1)) {
                player.jump()
            }

        }

    }

}
