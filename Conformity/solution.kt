import java.util.Scanner

fun main(){
	val num = readLine()!!.toInt()

	val aMap = mutableMapOf<MutableSet<String>, Int>()

	for (i in 0 until num){
		val aSet = readLine()!!.split(" ").toMutableSet()
		//println(aSet)
		//println(aMap)
		if (aSet !in aMap) aMap[aSet] = 1
		else if (aSet in aMap){
			val c = aMap[aSet]!!
			aMap[aSet] = c + 1
		}
	}

	val aList = aMap.values.toIntArray()
	var max : Int = 0
	for (i in aList){
		if (i >= max) max = i
	}

	var result = 0
	for (i in aList){
		if (i == max) result += i
	}
	println(result)
}