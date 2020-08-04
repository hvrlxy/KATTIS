fun main(){
	val aString = readLine()!!

	var aMap = mutableMapOf<Char, Int>()

	for (c in aString){
		if (aMap.containsKey(c)){
			aMap[c] = aMap[c]!! + 1
		}
		else{
			aMap[c] = 1
		}
	}

	val aList = (aMap.values).toIntArray()
	aList.sort()
	if (aList.size <= 2){
		println(0)
	}
	else{
		aList[aList.size - 1] = 0
		aList[aList.size - 2] = 0
		println(aList.sum())
	}
}