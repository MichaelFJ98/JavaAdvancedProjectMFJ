package components

import com.soywiz.korge.ui.*
import com.soywiz.korge.view.*


class Score : Container() {
    private lateinit var textField: UIText
    private var score: Double = 0.0

    fun addUI(layer: SContainer){
        textField = layer.uiText("Score: $score"){
            textSize = 30.0
            position(10.0,25.0)
        }
    }

    fun increase(layer: SContainer){
        score++
        textField.removeFromParent()
        addUI(layer)

    }

    fun getScore(): Double{
        return score
    }
}
