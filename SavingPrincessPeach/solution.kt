fun main(){
	val aline = readLine()!!.split(" ").map{it.toInt()}

	val obstacleSet = mutableSetOf<Int>()
	repeat(aline[1]){
		obstacleSet.add(readLine()!!.toInt())
	}
	for (i in 0 until aline[0]){
		if (i !in obstacleSet) println(i)
	}
	println("Mario got ${obstacleSet.size} of the dangerous obstacles.")
}