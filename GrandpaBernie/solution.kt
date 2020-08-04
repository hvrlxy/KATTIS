import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))
	val aMap = mutableMapOf<String, MutableList<Int>>()
	val numTrips = rd.readInt()

	repeat(numTrips){
		val a = rd.readStrings()

		if (a[0] !in aMap){
			aMap[a[0]] = mutableListOf(a[1].toInt())
		}
		else{
			aMap[a[0]]!!.add(a[1].toInt())
		}
	}

	for (i in aMap.keys){
		aMap[i]!!.sort()
	}

	val queries = rd.readInt()
	var result = StringBuilder()
	repeat(queries){
		val a = rd.readStrings()
		result.append("${aMap[a[0]]!![a[1].toInt() - 1]}\n")
	} 
	print(result)
}