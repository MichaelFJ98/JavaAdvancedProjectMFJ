class Player {
    val defaultHeight:Double = 92.0;
    var height:Double = defaultHeight;
    val xPixel:Double = 100.0;

    fun setYPos(jumpHeight: Double){
        this.height =+ jumpHeight
    }

    fun resetHeight(){
        this.height = defaultHeight;
    }
}
