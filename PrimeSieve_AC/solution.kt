import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()

    val bArray = java.util.BitSet(a[0]+1)

    var numPrime = 0
    bArray.flip(2, a[0] + 1)

    val sq = sqrt(a[0].toDouble()).roundToInt()
    for (i in 2 .. sq){
        if (bArray[i]){
            var j = i
            while (i * j <= a[0]){
                bArray.clear(i * j)
                j ++
            }
        }
    }

    for (i in 2 .. bArray.size()){
    	if (bArray[i]) numPrime ++
    }

	println(numPrime)
	repeat(a[1]){
		val num = rd.readInt()

		if (bArray[num]) println("1")
		else println("0")
	}
}