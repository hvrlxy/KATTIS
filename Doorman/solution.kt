import kotlin.math.abs

fun main(){
	val max = readLine()!!.toInt()

	val aString = readLine()!!

	var numW = 0
	var numM = 0

	var aLine = mutableListOf<Int>()
	for (i in 0 until aString.length){
		if (aString[i] == 'W') aLine.add(1) else aLine.add(0)
	}

	fun tryIdx(i: Int): Int{
		if (aLine[i] == 0) return (abs(numM + 1 - numW)) else return abs(numW + 1 - numM)
	}

	fun add(i: Int){
		if (aLine[i] == 1) numW ++ else numM ++
		aLine.removeAt(i)
	}

	while (aLine.size > 1){
		if (tryIdx(0) > max && tryIdx(1) > max) break
		if (tryIdx(0) > tryIdx(1)) add(1) else add(0)
	}

	if (aLine.size == 1 && tryIdx(0) <= max) add(0) 

	println(numW + numM)
}