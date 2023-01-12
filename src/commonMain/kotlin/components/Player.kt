package components

import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.vector.*
import org.jbox2d.dynamics.*


class Player(positionX: Double, positionY: Double) : Container() {

    enum class State {
        ALIVE,
        BOOSTED,
        DEAD
    }

    lateinit var state: State
    lateinit var drawModel: Sprite

    val defaultHeight:Double = 92.0
    val playerWidth = 66.0
    val jumpHeight: Double = 69.0
    val valueX = positionX
    val valueY = positionY - defaultHeight

    //draw player model
    suspend fun draw(layer: SContainer){
        //bitmap
       val playerBitmap = resourcesVfs["p2_stand.png"].readBitmap()

        //state
        state = State.ALIVE

        //draw the sprite on the correct layer with given x value and y value
        drawModel = layer.sprite(playerBitmap)
            .position(valueX,valueY)
            .registerBodyWithFixture(type = BodyType.DYNAMIC)
            hitShape {
                circle(drawModel.x, drawModel.y, playerWidth / 2)
            }

        //give player object their value
        this.x = drawModel.x
        this.y = drawModel.y


    }

    fun jump(){
        drawModel.position(valueX,valueY - jumpHeight)
    }

    //update player object Y value to the sprite y -value
    fun update(){
        this.y = drawModel.y
    }

   fun die(){
        state = State.DEAD
    }

}
