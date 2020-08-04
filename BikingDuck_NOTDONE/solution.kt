import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toLong() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	fun dist (x1: Int, y1: Int, x2: Int, y2: Int): Double = sqrt((x1 - x2).toDouble() * (x1 - x2) + (y1 - y2) * (y1 - y2))

	val velocity = rd.readInts()

	val boundaries = rd.readInts()
	val Gladstone = rd.readInts()
	val Daisy = rd.readInts()

	val numBike = rd.readInt()

	val bikeLocations = mutableListOf<Pair<Int, Int>>()
}