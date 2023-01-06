import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korgw.*
import com.soywiz.korim.atlas.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.interpolation.*
import org.jbox2d.dynamics.*
import com.soywiz.klock.milliseconds
import com.soywiz.klogger.Console
import com.soywiz.korev.Key
import com.soywiz.korge.animate.*
import com.soywiz.korge.input.onClick

const val WIDTH = 800;
const val HEIGHT = 800;
const val TITLE = "JavaAdvancedProjectMFj";
suspend fun main() = Korge(
    width = WIDTH, height = HEIGHT,
    bgcolor = Colors["#2b2b2b"],
    quality = GameWindow.Quality.PERFORMANCE,
    title = TITLE) {

    val sceneContainer = sceneContainer()

    sceneContainer.changeTo({ MainScene() })
}

class MainScene : Scene() {
    override suspend fun SContainer.sceneMain() {
        //Variables
        val groundLevel:Double = 600.0 //Y-value of ground level
        val jumpHeight:Double = 50.0 // # pixels you jump
        val playerStartPosition = 100.0
        val playerCharacter = Player(playerStartPosition)

        //Sprites
        val backgroundSprite:Bitmap = resourcesVfs["background.jpg"].readBitmap()
        val playerSprite: Bitmap = resourcesVfs["p2_stand.png"].readBitmap()
        val spikeSprite: Bitmap = resourcesVfs["IndividualSpike.png"].readBitmap()

        //Physical game objects
        sprite(backgroundSprite)
            .position(0,0)
            .setSize(800.0,600.0)

        solidRect(800, 200, Colors.WHITE)
            .position(0.0, groundLevel)
            .registerBodyWithFixture(type = BodyType.STATIC)

        val playerCharacterModel = sprite(playerSprite)
            .position(playerCharacter.valueX, groundLevel - playerCharacter.defaultHeight)
            .registerBodyWithFixture(type = BodyType.DYNAMIC)

        val spikeModel = sprite(spikeSprite)
            .position(700, 575)
            .registerBodyWithFixture(type= BodyType.KINEMATIC)
            .size(25,25)



        //Updates on input.
        addUpdater {
            if (input.keys[Key.SPACE] && playerCharacterModel.y >= ((groundLevel - playerCharacter.defaultHeight)- 1)) {
                playerCharacter.setYPos(playerCharacter.valueX + jumpHeight)
                playerCharacterModel.position(playerCharacter.valueX, groundLevel - playerCharacter.height)
                playerCharacter.resetHeight()

            }
        }

    }
}
