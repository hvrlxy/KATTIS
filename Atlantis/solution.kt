import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

/*
	I use java queue in this implementation for speed, but feel free to use ArrayDeque 
	or mutableList
*/

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    data class Store(val t: Int, val h: Int)

    val numStore = rd.readInt()
    val storeList = mutableListOf<Store>()

    repeat(numStore){
    	val a = rd.readInts()

    	storeList.add(Store(a[0], a[1]))
    }

    storeList.sortBy{it.t}
    storeList.sortBy{it.h}

    println(storeList)

    var currentTime = 0
    var result = 0
    for (s in storeList){
    	if (currentTime + s.t <= s.h){
    		result ++
    		currentTime += s.t

    		println(s)
    		println(currentTime)
    	}
    }

    println(result)
}