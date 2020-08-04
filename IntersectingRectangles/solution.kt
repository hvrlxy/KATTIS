import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numRect = rd.readInt()
	val aList = mutableListOf<IntArray>()

	repeat(numRect){
		val a = rd.readInts().toIntArray()
		aList.add(a)
	}

	aList.sortBy{it[1]}

	//println(aList.map {it.joinToString()})

	fun isIntersect (rect1: IntArray, rect2: IntArray): Boolean{
		if ((rect1[0] in rect2[0]..rect2[2] && rect1[3] in rect2[1]..rect2[3]) ||
			(rect1[2] in rect2[0]..rect2[2] && rect1[3] in rect2[1]..rect2[3]) ||
			(rect1[0] in rect2[0]..rect2[2] && rect1[1] in rect2[1]..rect2[3]) ||
			(rect1[2] in rect2[0]..rect2[2] && rect1[1] in rect2[1]..rect2[3])){
			if (rect1[0] in rect2[0]..rect2[2] && rect1[3] in rect2[1]..rect2[3]) println("1")
			else if (rect1[2] in rect2[0]..rect2[2] && rect1[3] in rect2[1]..rect2[3]) println("2")
			else if (rect1[0] in rect2[0]..rect2[2] && rect1[1] in rect2[1]..rect2[3]) println("3")
			else if (rect1[2] in rect2[0]..rect2[2] && rect1[1] in rect2[1]..rect2[3]) println("4")
		}
		return ((rect1[0] in rect2[0]..rect2[2] && rect1[3] in rect2[1]..rect2[3]) ||
			(rect1[2] in rect2[0]..rect2[2] && rect1[3] in rect2[1]..rect2[3]) ||
			(rect1[0] in rect2[0]..rect2[2] && rect1[1] in rect2[1]..rect2[3]) ||
			(rect1[2] in rect2[0]..rect2[2] && rect1[1] in rect2[1]..rect2[3]))
	}

	for (i in 0 until numRect - 1){
		if (isIntersect(aList[i], aList[i + 1])) return println(1)
	}

	aList.sortBy{it[0]}

	for (i in 0 until numRect - 1){
		if (isIntersect(aList[i], aList[i + 1])) return println(1)
	}

	aList.sortBy{it[2]}

	for (i in 0 until numRect - 1){
		if (isIntersect(aList[i], aList[i + 1])) return println("${aList[i].joinToString()}, ${aList[i + 1].joinToString()}")
	}

	aList.sortBy{it[3]}

	for (i in 0 until numRect - 1){
		if (isIntersect(aList[i], aList[i + 1])) return println(1)
	}
	println(0)
}