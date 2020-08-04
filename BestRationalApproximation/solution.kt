import kotlin.math.*
import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()
	repeat(numCase){
		val array = rd.readStrings()

		val irrational = array[2].toDouble()
		val maxDenominator = array[1].toInt()
		println("maxDenominator: $maxDenominator")
		var currentP = 0
		var currentQ = 1
		for (i in maxDenominator downTo 1){
			val p = (irrational * i.toDouble()).toInt()
			if (abs(irrational - p.toDouble() / i) < abs(irrational - currentP/currentQ)){
				currentP = p.toInt()
				currentQ = i.toInt()
			}
			if (abs(irrational - (p + 1).toDouble() / i) < abs(irrational - currentP/currentQ)){
				currentP = p + 1
				currentQ = i.toInt()
			}
		}

		println("${array[0]} $currentP/$currentQ")
	}
}