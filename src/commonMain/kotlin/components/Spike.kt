package components

import com.soywiz.korge.view.*


interface Spike {

suspend fun draw(layer: SContainer)

fun update()

fun getView():Sprite

}
