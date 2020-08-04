fun main(){
	val aline = readLine()!!.split(" ").map{it.toInt()}

	val lightList = mutableListOf<Triple<Int, Int, Int>>()
	repeat(aline[0]){
		val light = readLine()!!.split(" ").map{it.toInt()}
		lightList.add(Triple(light[0], light[1], light[2]))
	}

	var currentPosition = 0
	var totalTime = 0
	for (l in lightList){
		totalTime += l.first - currentPosition
		currentPosition = l.first
		while (totalTime % (l.second + l.third) < l.second){
			totalTime ++
		}
		//if (!isRed) println(totalTime)
	}
	println(totalTime + aline[1] - currentPosition)
}