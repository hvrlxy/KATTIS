import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() }.toList() // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var n = rd.readInt()
	while (n != 0){
		var sumX = 0.0
		var sumY = 0.0

		repeat(n){
			val a = rd.readInts()
			sumX += a[0]
			sumY += a[1]
		}

		val exactX = sumX / n
		val exactY = sumY / n

		var currentX = exactX.toInt()
		var currentY = exactY.toInt()

		fun dist (x1: Double, y1: Double, x2: Int, y2: Int): Double = kotlin.math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))

		if (dist(exactX, exactY, currentX, currentY) - dist(exactX, exactY, exactX.toInt(), exactY.toInt() + 1) > 1e-8){
			currentY = exactY.toInt() + 1
		}

		if (dist(exactX, exactY, currentX, currentY) - dist(exactX, exactY, exactX.toInt() + 1, exactY.toInt()) > 1e-8){
			currentX = exactX.toInt() + 1
		}

		if (dist(exactX, exactY, currentX, currentY) - dist(exactX, exactY, exactX.toInt() + 1, exactY.toInt() + 1) > 1e-8){
			currentY = exactY.toInt() + 1
			currentX = exactX.toInt() + 1
		}

		println("$currentX $currentY")
		n = rd.readInt()
	}
}