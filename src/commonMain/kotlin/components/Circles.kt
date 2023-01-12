package components

import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import kotlin.random.*

class Circles : Container(){
    enum class State {
        ALIVE,
        DEAD
    }

    private val innerCirleRad: Double = 50.0
    private lateinit var innerCircle: Circle
    lateinit var state: State

    fun init(layer: SContainer){
        val randomX = Random.nextDouble(50.0,750.0)
        val randomY = Random.nextDouble(200.0,350.0)
        state = State.ALIVE
        innerCircle= layer
            .circle(innerCirleRad, Colors.WHITE, Colors.BLUE, 5.0)
            .position(randomX,randomY)

    }

    fun getViewInner():Circle{
        return innerCircle
    }




    fun update(){
        if(innerCircle.radius > 0.0){
            innerCircle.radius -= .5
        }
        else{
            state = State.DEAD

        }
    }
}
