import java.io.*
import kotlin.math.abs

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numCase = rd.readInt()

	repeat(numCase){
		val n = rd.readLn()

		val firstHalf = (n.toInt() / 1000).toString()
		val firstHalfInt = firstHalf.toInt()

		val a1 = (firstHalf + firstHalf.reversed()).toInt()
		val a2 = ((firstHalfInt - 1).toString() + (firstHalfInt - 1).toString().reversed()).toInt()
		val a3 = ((firstHalfInt + 1).toString() + (firstHalfInt + 1).toString().reversed()).toInt()

		//println("$a1 $a2 $a3")

		if (abs(a2 - n.toInt()) <= abs(a1 - n.toInt()) && abs(a2 - n.toInt()) <= abs(a3 - n.toInt())) println(a2)
		else if (abs(a1 - n.toInt()) < abs(a2 - n.toInt()) && abs(a1 - n.toInt()) <= abs(a3 - n.toInt())) println(a1)
		else println(a3)
	}
}