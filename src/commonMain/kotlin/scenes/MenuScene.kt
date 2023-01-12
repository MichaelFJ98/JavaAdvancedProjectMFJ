package scenes

import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korge.input.*
import com.soywiz.korge.ui.*
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

        uiText("Press the 'A' button on your keyboard before the circle closes to unlock a boost."){
            textSize = 20.0
            position(100.0 , 600.0)

        }


        button.onClick {
            sceneContainer.changeTo({ PlayScene() })
        }


    }
}

