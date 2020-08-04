fun main(){
	val numArray = readLine()!!.split("+").map {it.toLong()}

	val resultSet = mutableSetOf<Long>()

	var currentValue = 0L
	var aString = "0"

	fun tryPlus(pos: Int){
		currentValue += aString.toLong()
		aString = "${numArray[pos]}"
	}

	fun tryConcatenate(pos: Int){
		aString += "${numArray[pos]}"
	}

	fun search(pos: Int){
		if (pos == numArray.size) {
			if (currentValue !in resultSet) resultSet.add(currentValue)
			currentValue = 0L
			aString = "0"
			return
		}
		val oldValue = currentValue
		tryPlus(pos)
		search(pos + 1)
		currentValue = oldValue

		tryConcatenate(pos)
		search(pos + 1)
		currentValue = oldValue
	}

	search(0)

	println(resultSet)
	println(resultSet.size)
}