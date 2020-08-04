import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine() // string line
private fun BufferedReader.readInt() = readLn()!!.toInt() // single int
private fun BufferedReader.readStrings() = readLn()!!.split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun generatePrime(upperbound: Int): MutableSet<Int>{
	val bArray = java.util.BitSet(upperbound+1)

    val primeSet = mutableSetOf<Int>()
    bArray.flip(2, upperbound + 1)

    val sq = sqrt(upperbound.toDouble()).roundToInt()
    for (i in 2 .. sq){
        if (bArray[i]){
            var j = i
            while (i * j <= upperbound){
                bArray.clear(i * j)
                j ++
            }
        }
    }

    for (i in 2 .. bArray.size()){
    	if (bArray[i]) primeSet.add(i)
    }

    return primeSet
}