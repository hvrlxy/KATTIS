fun main(){
	readLine()

	val numAlternatives = readLine()!!.toInt()
	val aList = Array<List<String>>(numAlternatives) { readLine()!!.split(", ").toList() }

	var resultDiff = Int.MAX_VALUE

	var resultList = mutableListOf<List<String>>()
	for (i in 0 until numAlternatives){
		var maxDiff = 0

		for (j in 0 until numAlternatives){
			var diff = 0
			for (k in 0 until aList[i].size){
				if (aList[i][k] != aList[j][k]) diff ++
			}
			if (diff > maxDiff) maxDiff = diff
		}

		if (maxDiff == resultDiff) resultList.add(aList[i])
		else if (maxDiff < resultDiff){
			resultDiff = maxDiff
			resultList = mutableListOf<List<String>>()
			resultList.add(aList[i])
		}
	}

	for (i in resultList){
		println(i.joinToString(separator = ", "))
	}
}