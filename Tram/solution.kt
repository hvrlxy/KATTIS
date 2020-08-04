import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numPoints = rd.readInt()
	var total = 0
	repeat(numPoints){
		val stats = rd.readInts()
		total += stats[1] - stats[0]
	}

	println(total.toDouble() / numPoints)
}