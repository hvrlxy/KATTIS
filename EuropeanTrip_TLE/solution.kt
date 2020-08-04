import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toDouble() } // list of ints

fun dist (x1: Double, y1: Double, x2: Double, y2: Double): Double = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val a = rd.readInts()
	val b = rd.readInts()
	val c = rd.readInts()

	val biggestX = maxOf(a[0], b[0], c[0])
	val biggestY = maxOf(a[1], b[1], c[1])

	var finalX = 0.0
	var finalY = 0.0

	var smallestDist = Double.MAX_VALUE

	var x = minOf(a[0], b[0], c[0])
	while (x < biggestX){
		var y = minOf(a[1], b[1], c[1])
		while (y < biggestY){
			val d1 = dist(a[0], a[1], x, y)
			val d2 = dist(b[0], b[1], x ,y)
			val d3 = dist(c[0], c[1], x, y)

			if (d1 + d2 + d3 < smallestDist) {
				smallestDist = d1 + d2 + d3
				finalY = y
				finalX = x
			}
			y += 0.001
		}
		x += 0.001
	}
	println("$finalX $finalY")
}