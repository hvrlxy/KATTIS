import java.io.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))
	val aMap = mutableMapOf<String, Int>()
	var population = 0
	var a = rd.readLn()
	while (a != null){
		if (a !in aMap.keys) aMap[a] = 1
		else{
			val c = aMap[a]!!
			aMap[a] = c + 1
		}
		population ++
		a = rd.readLn()
	}

	val array = aMap.keys.toMutableList()
	array.sort()
	var result = StringBuilder()
	for (i in 0 until array.size){
		result.append("${array[i]} ${aMap[array[i]]!!.toFloat()/population * 100F}\n")
	}
	print(result)
}