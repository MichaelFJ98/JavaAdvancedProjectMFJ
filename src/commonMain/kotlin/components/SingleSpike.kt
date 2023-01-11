package components

import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import org.jbox2d.dynamics.*

class SingleSpike(startingPos: Double): Container(),Spike {
    lateinit var drawSpike: Sprite

    val spikeheight = 25.0
    val spikeWidth = 25.0
    val startingPosY = 600.0 - spikeheight

    val startingPosX = startingPos


    suspend fun draw(layer: SContainer){
        val bitmap = resourcesVfs["IndividualSpike.png"].readBitmap()

        drawSpike = layer.sprite(bitmap)
            .position(startingPosX,startingPosY)
            .registerBodyWithFixture(type = BodyType.KINEMATIC)
            .size(spikeWidth, spikeheight)

        this.x = drawSpike.x
        this.y = drawSpike.y

    }
    fun update(){
        drawSpike.x--
        this.x = drawSpike.x
    }
}
