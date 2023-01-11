package components

import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import org.jbox2d.dynamics.*
class Spikes{
    val spikesList = mutableListOf<Sprite>()
    suspend fun addSpike(layer: SContainer){
        val spike = Spike().drawSingular(layer)
        spikesList.add(spike)
    }

    fun update(){
        for(spike in spikesList){
            if(spike.x < 0.0){
                spikesList.remove(spike)
            }
        }
    }
}
class Spike: Container() {
    lateinit var drawSingularSpike: Sprite
    lateinit var drawMultiSpike: Sprite

    val spikeheight = 25.0
    val spikeWidth = 25.0

    val startingPosX = 1000.0
    val startingPosY = 600.0 - spikeheight
    suspend fun drawSingular(layer: SContainer):Sprite{
        val singularBitmap = resourcesVfs["IndividualSpike.png"].readBitmap()

        drawSingularSpike = layer.sprite(singularBitmap)
                                        .position(startingPosX,startingPosY)
                                        .registerBodyWithFixture(type = BodyType.KINEMATIC)
                                        .size(spikeheight,spikeWidth)

        this.x = drawSingularSpike.x
        this.y = drawSingularSpike.y



        return drawSingularSpike
    }

    suspend fun drawMulti(layer: SContainer){
        val multiBitmap = resourcesVfs["4ConjoinedSpikes"].readBitmap()

        drawMultiSpike = layer.sprite(multiBitmap)
                                    .position(startingPosX,startingPosY)
                                    .registerBodyWithFixture(type = BodyType.KINEMATIC)
                                    .size(spikeheight,spikeWidth)

        this.x = drawMultiSpike.x
        this.y = drawMultiSpike.y
    }

    fun updateSingular(){
        drawSingularSpike.x--
        this.x = drawSingularSpike.x
    }

    fun updateMulti(){
        drawMultiSpike.x--
        this.x = drawMultiSpike.x
    }



}
