package scenes

import components.Player
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


class PlayScene : Scene() {
    //Variables
    val groundLevel:Double = 600.0 //Y-value of ground level
    val jumpHeight:Double = 50.0 // # pixels you jump
    val playerStartPosition = 100.0


    private lateinit var player: Player

    override suspend fun SContainer.sceneMain() {

        //Sprites
        val backgroundSprite: Bitmap = resourcesVfs["background.jpg"].readBitmap()



        player = Player(playerStartPosition, groundLevel)
        player.loadPlayer()
        addChild(player.drawModel)

        solidRect(800, 200, Colors.WHITE)
            .position(0.0, groundLevel)
            .registerBodyWithFixture(type = BodyType.STATIC)

        //Physical game objects





        //Updates on input.
        addUpdater {
//            player.y = player.drawModel.y
            println("drawmodel: ${player.drawModel.y}")
            println("playerobject: ${player.y}")

            if (input.keys[Key.SPACE] && player.y >= ((groundLevel - player.defaultHeight)- 1)) {
                player.y -= 50.0
                player.drawModel.y -= 50.0
            }


        }

    }

}
