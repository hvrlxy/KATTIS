fun main(){
	val aString = readLine()!!
	var aList = Array<Pair<Int,Int>>(10){(it to 0)}

	for (i in 0 until aString.length){
		var n = aList[aString[i].toString().toInt()].second + 1
		aList[aString[i].toString().toInt()] = (aString[i].toString().toInt() to n)
	}

	var minPair = (-1 to Int.MAX_VALUE)
	for (i in 0 until 10){
		if (aList[i].second < minPair.second) minPair = aList[i]
	}

	if (minPair.second == 0) println(minPair.first)
	else{
		println("1${"0".repeat(minPair.second - 1)}${minPair.first}")
	}
}