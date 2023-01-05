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


suspend fun main() = Korge(width = 800, height = 800,
    bgcolor = Colors["#2b2b2b"],
    quality = GameWindow.Quality.PERFORMANCE, title = "JavaAdvancedProjectMFJ") {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
    override suspend fun SContainer.sceneMain() {
        val groundLevel:Double = 600.0 //Y-value of ground level
        val jumpHeight:Double = 50.0 // # pixels you jump
        val p1 = Player()

        val playerSprite: Bitmap = resourcesVfs["p2_stand.png"].readBitmap()

        val test = sprite(playerSprite)
            .position(p1.xPixel, groundLevel - p1.height)
            .registerBodyWithFixture(type = BodyType.DYNAMIC)

        solidRect(800, 100, Colors.WHITE)
            .position(0.0, groundLevel)
            .registerBodyWithFixture(type = BodyType.STATIC)

        //todo merge spite and player class in 1
        
        addUpdater {
            if (input.keys[Key.SPACE] && test.y >= ((groundLevel - p1.defaultHeight)- 1)) {
                p1.setYPos(p1.xPixel + jumpHeight)
                test.position(p1.xPixel, groundLevel - p1.height)
                p1.resetHeight()
            }
        }

    }
}
