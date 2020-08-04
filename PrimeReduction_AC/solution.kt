import java.io.*
import kotlin.math.sqrt

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	var x = rd.readInt()
	var newX = 0
	var count = 1

	fun isPrime(x: Int): Boolean{
		var x = x
		var isPrime = true
		val sqrt = sqrt(x.toDouble()).toInt()
		for (i in 2 .. sqrt){
			while (x % i == 0){
				isPrime = false
				newX += i
				x /= i
			}
		}
		if (x > 1) newX += x
		return isPrime
	}
	var result = StringBuilder()
	while (x != 4){
		newX = 0
		count = 1
		while (!isPrime(x)){
			count ++
			x = newX
			newX = 0
		}
		result.append("$x $count\n")
		x = rd.readInt()
	}
	print(result)
}