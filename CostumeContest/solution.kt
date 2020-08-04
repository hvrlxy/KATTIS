import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val numPeople = rd.readInt()

    val aMap = mutableMapOf<String, Int>()
    repeat(numPeople){
    	val costume = rd.readLn()
    	if (costume !in aMap) aMap[costume] = 1
    	else {
    		val c = aMap[costume]!!
    		aMap[costume] = c + 1
    	}
    }

    val keys = aMap.keys.toMutableList()
    keys.sort()
    keys.sortBy{aMap[it]!!}

    val min = aMap[keys[0]]!!
    for (i in keys){
    	if (aMap[i]!! == min) println(i) else return
    }
}