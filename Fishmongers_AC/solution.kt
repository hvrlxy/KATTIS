import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val a = rd.readInts()

    val fishList = rd.readInts().toMutableList()
    fishList.sort()

    data class Fishmonger(var num: Long, var money: Long)

    val fishmongersList = mutableListOf<Fishmonger>()

    repeat(a[1]){
    	val a1 = rd.readInts()
    	fishmongersList.add(Fishmonger(a1[0].toLong(), a1[1].toLong()))
    }

    fishmongersList.sortBy{it.money}

    var result = 0L
    while (fishList.isNotEmpty() && fishmongersList.isNotEmpty()){
    	repeat(fishmongersList[fishmongersList.size - 1].num.toInt()){
    		if (fishList.isEmpty()) return println(result)
    		result += fishList[fishList.size - 1] * fishmongersList[fishmongersList.size - 1].money
    		fishList.removeAt(fishList.size - 1)
    	}
    	fishmongersList.removeAt(fishmongersList.size - 1)
    }
    println(result)
}