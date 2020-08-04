fun main(){
	var aString = readLine()

	val wordList = mutableListOf<String>()
	while (aString != null){
		val aline = aString.split(" ").toList()
		wordList.addAll(aline)
		aString = readLine()
	}

	val resultSet = mutableSetOf<String>()
	for (i in 0 until wordList.size){
		for (j in 0 until wordList.size){
			if (i != j) resultSet.add(wordList[i] + wordList[j])
		}
	}

	val resultList = resultSet.toList().sorted()
	println(resultList.joinToString(separator = "\n"))
}