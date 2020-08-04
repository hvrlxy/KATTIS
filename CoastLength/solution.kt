fun main(){
	val stats = readLine()!!.split(" ").map{it.toInt()}

	val aSet = mutableSetOf<Pair<Int, Int>>()
	for (i in 0 until stats[0]){
		val aString = readLine()!!
		for (j in 0 until stats[1]){
			if (aString[j] == '1') aSet.add(i to j)
		}
	}

	var result = 0
	for (c in aSet){
		result += 4
		if ((c.first - 1 to c.second) in aSet) result --
		if ((c.first + 1 to c.second) in aSet) result --
		if ((c.first to c.second - 1) in aSet) result --
		if ((c.first to c.second + 1) in aSet) result --
	}

	println(result)
}             