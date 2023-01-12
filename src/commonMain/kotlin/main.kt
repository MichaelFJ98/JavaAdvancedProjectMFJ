import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korgw.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import components.*
import scenes.*



const val WIDTH = 800
const val HEIGHT = 800
const val TITLE = "JavaAdvancedProjectMFj"
private lateinit var backgroundBitmap: Bitmap
private lateinit var bg: Image
private lateinit var score: Score
suspend fun main() = Korge(
    width = WIDTH, height = HEIGHT,
    bgcolor = Colors["#2b2b2b"],
    quality = GameWindow.Quality.PERFORMANCE,
    title = TITLE) {

    backgroundBitmap = resourcesVfs["background.jpg"].readBitmap()
    bg = image(backgroundBitmap) {
        scaledWidth = 800.0
        scaledHeight = 800.0
    }

    val sceneContainer = sceneContainer()

    sceneContainer.changeTo({ MenuScene() })
}

