import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))
	
	rd.readInt()
	val array = rd.readInts()

	val aMap = mutableMapOf<Int, Int>()

	for (i in 0 until array.size){
		val value = array[i] + 1
		if (value in aMap && aMap[value]!! == 1){
			aMap.remove(value)
		}
		else if (value in aMap && aMap[value]!! >= 1){
			val c = aMap[value]!! - 1
			aMap[value] = c
		}
		if (array[i] in aMap){
			val c = aMap[array[i]]!! + 1
			aMap[array[i]] = c
		}
		else{
			aMap[array[i]] = 1
		}
	}
	//println(aMap)
	println(aMap.values.sum())
}