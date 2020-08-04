import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val stats = rd.readInts()

	val days = rd.readInts().toIntArray()

	var maxValue = 0
	var result = 0

	for (i in 0 until stats[0]){
		days[i] = 100 * days[i] + stats[1] * i
		maxValue = max(maxValue, days[i])

		//println("${days[i]} $maxValue")
		result = max (result, maxValue - days[i] - stats[1])
	}

	println(max(result, 0))
}