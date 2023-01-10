package components

import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import org.jbox2d.dynamics.*


class Player(positionX: Double, positionY: Double) : Container() {

    enum class State {
        ALIVE,
        BOOSTED,
        DEAD
    }

    var movementSpeed = 100
    lateinit var state: State
    lateinit var drawModel: Sprite


    val defaultHeight:Double = 92.0
    var modelHeight:Double = defaultHeight
    val valueX = positionX
    val valueY = positionY - defaultHeight

    private lateinit var playerModel: Image

    suspend fun loadPlayer(){
        state = State.ALIVE

        val playerModelSprite = resourcesVfs["p2_stand.png"].readBitmap()

        drawModel = sprite(playerModelSprite)
            .position(valueX,valueY)
            .registerBodyWithFixture(type = BodyType.DYNAMIC)
//        this.x = valueX
//        this.y = valueY
    }
    fun getYVal():Double{
        return drawModel.y
    }

    fun returnModel():Sprite{
        return drawModel
    }



    fun die(onDie: () -> Unit){
        state = State.DEAD

        removeChildren()
    }
    fun setYPos(jumpHeight: Double){
        this.modelHeight =+ jumpHeight
    }

    fun resetHeight(){
        this.modelHeight = defaultHeight;
    }

}
