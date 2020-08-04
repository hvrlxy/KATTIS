fun main(){
	readLine()
	val array = readLine()!!.split(" ")

	val frequencyMap = mutableMapOf<String, Int>()
	val appearanceMap = mutableMapOf<String, Int>()

	for (i in 0 until array.size){
		if (array[i] !in frequencyMap.keys){
			appearanceMap[array[i]] = i
			frequencyMap[array[i]] = 1
		}
		else{
			val c = frequencyMap[array[i]]!!
			frequencyMap[array[i]] = c + 1
		}
	}

	val keysArray = frequencyMap.keys.toMutableList()

	keysArray.sortBy{appearanceMap[it]!!}
	keysArray.reverse()
	keysArray.sortBy{frequencyMap[it]!!}

	val result = StringBuilder()
	for (i in keysArray.size - 1 downTo 0){
		result.append("${keysArray[i]} ".repeat(frequencyMap[keysArray[i]]!!))
	}
	result.deleteCharAt(result.length - 1)
	println(result)
}