package components

import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import org.jbox2d.dynamics.*

class Ground: Container() {

    fun draw(layer: SContainer){
        layer.solidRect(800, 200, Colors.WHITE)
                     .position(0.0, 600.0)
                     .registerBodyWithFixture(type = BodyType.STATIC)
    }
}
