import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val aList = rd.readStrings()
    val aMap = mutableMapOf<String, Int>()

    for (i in 0 until aList.size){
    	aMap[aList[i]] = i
    }

    val songList = mutableListOf<MutableList<String>>()
    val numSongs = rd.readInt()
    repeat(numSongs){
    	songList.add(rd.readStrings().toMutableList())
    }

    val criteria = rd.readInt()
    for (i in 0 until criteria){
    	val c = rd.readLn()
    	songList.sortBy{it[aMap[c]!!]}
    	println(aList.joinToString(separator = " "))
	    for (i in songList){
	    	println(i.joinToString(separator = " "))
	    }

	    if (i < criteria - 1) println()
    }
}