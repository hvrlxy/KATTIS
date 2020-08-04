import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		val stats = rd.readInts()

		var currentArea = 0.0
		var currentCircle = 1
		var currentR = stats[0].toDouble()

		currentArea += currentCircle * (PI * currentR * currentR)
		if (stats[1] == 1) println(currentArea)
		else if (stats[1] == 2) {
			currentR /= 2.0
			currentCircle = 4
			currentArea += currentCircle * (PI * currentR * currentR)

			println(currentArea)
		}
		else{
			currentR /= 2.0
			currentCircle = 4
			var currentAdd = currentCircle * (PI * currentR * currentR)
			currentArea += currentAdd
			var iteration = stats[1] - 2
			while (iteration > 0){
				currentAdd *= 0.75
				currentArea += currentAdd
				iteration --
			}

			println(currentArea)
		}
	}
}