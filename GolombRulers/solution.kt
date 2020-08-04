import kotlin.math.abs

fun main(){
	var aString = readLine()
	while (aString != null){
		val aList = aString.split(" ").map{ it.toInt() }.toIntArray()
		aList.sort()
		val booleanArray = BooleanArray(aList[aList.size - 1] + 1){ false }

		var notARuler = false
		var missing = mutableListOf<Int>()
		loop@for (i in 0 until aList.size){
			for (j in i + 1 until aList.size){
				val d = abs(aList[i] - aList[j])
				if (booleanArray[d] == true){
					notARuler = true
					break@loop
				}
				else{
					booleanArray[d] = true
				}
			}
		}

		if (!notARuler){
			for (i in 1 until aList[aList.size - 1]){
				if (booleanArray[i] == false) missing.add(i)
			}
			if (missing.size == 0) println("perfect")
			else println("missing ${missing.joinToString(separator = " ")}")
		}
		else println("not a ruler")

		aString = readLine()
	}
}