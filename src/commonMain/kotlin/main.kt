import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korgw.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import scenes.*



const val WIDTH = 800;
const val HEIGHT = 800;
const val TITLE = "JavaAdvancedProjectMFj";
suspend fun main() = Korge(
    width = WIDTH, height = HEIGHT,
    bgcolor = Colors["#2b2b2b"],
    quality = GameWindow.Quality.PERFORMANCE,
    title = TITLE) {
    val backgroundSprite: Bitmap = resourcesVfs["background.jpg"].readBitmap()
    val bg = image(backgroundSprite).apply {
        scale = .1


    }
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo({ PlayScene() })
}

