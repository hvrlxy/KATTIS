import java.io.*
import kotlin.math.*

private fun BufferedReader.readLn() = readLine()!! // string line
private fun BufferedReader.readInt() = readLn().toInt() // single int
private fun BufferedReader.readStrings() = readLn().split(" ") // list of strings
private fun BufferedReader.readInts() = readStrings().map { it.toInt() } // list of ints

fun main(){
	val rd = BufferedReader(InputStreamReader(System.`in`))

	val booleanArray = BooleanArray(50000){true}

	for (i in 2 until 50000){
		if (booleanArray[i]){
			var j = 2
			while (i * j < 50000){
				booleanArray[i * j] = false
				j ++
			}
		}
	}

	val primes = mutableListOf<Int>()
	for (i in 2 until 50000){
		if (booleanArray[i]) primes.add(i)
	}

	val numCase = rd.readInt()
	repeat(numCase){
		val aString = rd.readStrings()

		val aMap = mutableMapOf<Int, Int>()

		fun factor(n: Int){
			var n = n
			for (i in primes){
				while (n % i == 0){
					if (i !in aMap.keys) aMap[i] = 1
					else {
						val c = aMap[i]!!
						aMap[i] = c + 1
					}
					n /= i
					if (n == 1) return
				}
			}

			if (n > 1) aMap[n] = 1
		}

		factor(aString[0].toInt())
		//println(aMap)

		var winner = -1
		if (aMap.size == 1){
			val key = aMap.keys.toMutableList()
			if (aMap[key[0]]!! % 2 == 1) winner = 0 else winner = 1
		}
		else if (aMap.size == 2){
			val key = aMap.keys.toMutableList()
			val m = aMap[key[0]]!!
			val n = aMap[key[1]]!!

			if (m == n) winner = 1
			else if (abs(m - n) == 1) winner = 0
		}

		if (winner == -1) println("tie")
		else if (winner == 0) println(aString[1])
		else{
			if (aString[1] == "Alice") println("Bob") else println("Alice")
		}
	}
}







