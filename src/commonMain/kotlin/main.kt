import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korgw.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.interpolation.*
import org.jbox2d.dynamics.*

suspend fun main() = Korge(width = 800, height = 800,
    bgcolor = Colors["#2b2b2b"],
    quality = GameWindow.Quality.PERFORMANCE, title = "JavaAdvancedProjectMFJ") {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
    override suspend fun SContainer.sceneMain() {
        solidRect(600,100, Colors.WHITE).position(100,600).registerBodyWithFixture(
            type = BodyType.STATIC
        )
    }
}
