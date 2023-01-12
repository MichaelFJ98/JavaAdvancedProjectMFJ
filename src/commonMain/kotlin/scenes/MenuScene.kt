package scenes

import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korma.geom.shape.*
import org.jbox2d.dynamics.*
import com.soywiz.korev.*
import com.soywiz.korge.input.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*

class MenuScene : Scene() {
    private lateinit var buttonBitmap: Bitmap
    private lateinit var button: Image
    override suspend fun SContainer.sceneMain(){

        buttonBitmap = resourcesVfs["start.png"].readBitmap()

        button = image(buttonBitmap){
            scaledWidth = 200.0
            scaledHeight = 100.0
            centerOnStage()
        }



        button.onClick {
            sceneContainer.changeTo({ PlayScene() })
        }


    }
}

