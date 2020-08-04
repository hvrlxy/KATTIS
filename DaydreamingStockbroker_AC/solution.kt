import java.io.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val numDay = rd.readInt()
	var previousPrice = Int.MIN_VALUE
	var money = 100L
	repeat(numDay){
		var currentPrice = rd.readInt()
		if (currentPrice > previousPrice){
			money += kotlin.math.min(100000, money / previousPrice) * (currentPrice - previousPrice)
		}
		previousPrice = currentPrice
	}
	println(money)
}