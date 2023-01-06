class Player(positionX: Double) {
    val defaultHeight:Double = 92.0;
    var height:Double = defaultHeight;
    val valueX = positionX;

    fun setYPos(jumpHeight: Double){
        this.height =+ jumpHeight
    }

    fun resetHeight(){
        this.height = defaultHeight;
    }
}
