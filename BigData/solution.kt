import java.io.*
import java.util.LinkedList
import java.util.Queue
import kotlin.math.*

val prime = mutableListOf()

fun generate(){
	val bArray = java.util.BitSet(14000+1)

    var numPrime = mutableListOf<Int>()
    bArray.flip(2, 14000 + 1)

    val sq = sqrt(14000.toDouble()).roundToInt()
    for (i in 2 .. sq){
        if (bArray[i]){
            var j = i
            while (i * j <= 14000){
                bArray.clear(i * j)
                j ++
            }
        }
    }

    for (i in 2 .. bArray.size()){
    	if (bArray[i]) prime.add(i)
    }
}

fun primeFactor(n: Int): Int{
	var result = 0
	for (i in prime){
		if (n % i == 0) result ++
	}
	return result
}

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt()}.toMutableList() // list of ints

fun main(){
    val rd = BufferedReader(InputStreamReader(System.`in`))

    val N = rd.readInt()
    val S = rd.readInts()

    var result = 0
    for (i in 0 until N){
    	result += primeFactor(S[i])
    }
    println(result)
}