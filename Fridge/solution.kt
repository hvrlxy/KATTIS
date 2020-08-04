fun main(){
	val aString = readLine()!!
	var aList = Array<Pair<Int,Int>>(10){(it to 0)}

	for (i in 0 until aString.length){
		var n = aList[aString[i].toString().toInt()].second + 1
		aList[aString[i].toString().toInt()] = (aString[i].toString().toInt() to n)
	}

	var minPair = aList[1]
	for (i in 1 until 10){
		if (aList[i].second < minPair.second) minPair = aList[i]
	}

	if (minPair.second > aList[0].second) println("1${"0".repeat(aList[0].second + 1)}")
	else println(minPair.first.toString().repeat(minPair.second + 1))
}