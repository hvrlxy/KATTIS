fun main(){
	val numObservations = readLine()!!.toInt()
	var displaySecond = 0
	var realSecond = 0
	repeat(numObservations){
		val aline = readLine()!!.split(" ").map{it.toInt()}
		displaySecond += aline[0] * 60
		realSecond += aline[1]
	}

	if (realSecond <= displaySecond) println("measurement error")
	else println(realSecond.toDouble() / displaySecond)
}