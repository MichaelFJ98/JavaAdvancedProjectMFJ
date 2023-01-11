package components

import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.vector.*
import org.jbox2d.dynamics.*

class MultiSpike(startingPos: Double): Container(), Spike {
    lateinit var drawSpike: Sprite

    val spikeheight = 25.0
    val spikeWidth = 100.0
    val startingPosY = 600.0 - spikeheight

    val startingPosX = startingPos

    override suspend fun draw(layer: SContainer){
        val bitmap = resourcesVfs["4ConjoinedSpikes.png"].readBitmap()

        drawSpike = layer.sprite(bitmap)
            .position(startingPosX,startingPosY)
            .registerBodyWithFixture(type = BodyType.KINEMATIC)
            .size(spikeWidth,spikeheight)

        this.x = drawSpike.x
        this.y = drawSpike.y

    }

    override fun update(){
        drawSpike.x--
        this.x = drawSpike.x
    }

    override fun getView():Sprite{
        return drawSpike
    }

}
