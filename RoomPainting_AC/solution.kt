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

    val a = rd.readInts()
    val n = a[0]
    val m = a[1]

    val canList = mutableListOf<Int>()
    repeat(n){ canList.add(rd.readInt()) }
    canList.sort()

    val colorList = mutableListOf<Int>()
    repeat(m){ colorList.add(rd.readInt()) }
    colorList.sort()

    var result = 0L
    //println(colorList.joinToString(separator = "\n"))

    var currentCan = 0

    for (c in colorList){
    	for (i in currentCan until n){
    		if (canList[i] >= c){
    			//println("$c ${canList[i]} $currentCan")
    			currentCan = i
    			result += (canList[i] - c)
    			break
    		}
    	}
    }

    println(result)
}