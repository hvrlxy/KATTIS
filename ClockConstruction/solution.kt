import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

val INF = 100000000

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val s = rd.readInts()

    val N = s[0]
    val H = s[1]
    val W = s[2]

    var setList = mutableListOf<MutableSet<Int>>()

    val aSet = mutableSetOf<Int>()
    for (i in 0 until W * H){
    	aSet.add(i)
    }
    setList.add(aSet)

    repeat(N){
    	val pSet = mutableSetOf<Int>()
    	for (i in 0 until H){
    		val aline = rd.readLn()
    		for (j in 0 until W){
    			if (aline[j] == '*') pSet.add(i * W + j)
    		}
    	}
    	
    	setList.add(pSet)
    }

    println(setList)
}