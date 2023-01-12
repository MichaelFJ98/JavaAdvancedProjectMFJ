package components

import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import org.jbox2d.dynamics.*

class SingleSpike(startingPos: Double): Container(),Spike {
    private lateinit var drawSpike: Sprite

    private val spikeheight = 25.0
    private val spikeWidth = 25.0
    private val startingPosY = 600.0 - spikeheight

    private val startingPosX = startingPos


    override suspend fun draw(layer: SContainer){
        val bitmap = resourcesVfs["IndividualSpike.png"].readBitmap()

        drawSpike = layer.sprite(bitmap)
            .position(startingPosX,startingPosY)
            .registerBodyWithFixture(type = BodyType.KINEMATIC)
            .size(spikeWidth, spikeheight)

        this.x = drawSpike.x
        this.y = drawSpike.y

    }
    override fun update(){
        drawSpike.x--
        this.x = drawSpike.x
    }

    override fun getView():Sprite {
        return drawSpike
    }




}
